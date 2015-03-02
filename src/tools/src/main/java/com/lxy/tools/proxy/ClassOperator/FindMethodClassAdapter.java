package com.lxy.tools.proxy.ClassOperator;

import com.lxy.tools.proxy.commons.MethodDefine;
import com.lxy.tools.proxy.commons.MethodProxyDefine;
import com.lxy.tools.proxy.newCode.ICode;
import com.lxy.tools.proxy.newCode.NullCode;
import com.lxy.tools.utils.ALLUtils;
import com.lxy.tools.utils.Pair;
import org.objectweb.asm.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在一个类中找到需要加代理的函数，给他加上眼药水。。
 *
 * @author lxy
 */
public class FindMethodClassAdapter extends ClassAdapter {

    //	private String className;
    private String enhancedName;
    private List<MethodProxyDefine> mpds;
    private Map<String, Class<? extends ICode>> icodesFields;

    public FindMethodClassAdapter(ClassVisitor cv,
                                  List<MethodProxyDefine> mpds, String className) {
        super(cv);
        this.mpds = mpds;
        this.enhancedName = className;
        init();
    }

    private void init() {
//		if (!MyStringUtils.isEmpty(className)) {
//			className = className.replace(".", "/");
//		}
        icodesFields = new HashMap<String, Class<? extends ICode>>();

        if (mpds != null && mpds.size() > 0) {
            for (MethodProxyDefine mpd : mpds) {
                addFields(mpd.getProxies());
            }
        }
    }

    private void addFields(
            Pair<Class<? extends ICode>, Class<? extends ICode>> proxies) {
        Class<? extends ICode> proxy = proxies.getFirst();
        addFields2(proxy);

        proxy = proxies.getSecond();
        addFields2(proxy);
    }

    private void addFields2(Class<? extends ICode> proxy) {
        if (proxy != null && !proxy.isAssignableFrom(NullCode.class)) {
            String name = ALLUtils.getFieldName(proxy);
            if (icodesFields.get(name) == null) {
                icodesFields.put(name, proxy);
                createICodeFields(name, proxy);
            }
        }
    }

    private void createICodeFields(String name, Class<? extends ICode> proxy) {
        //TODO public->private/protected
        visitField(Opcodes.ACC_PUBLIC, name, Type.getDescriptor(proxy), null,
                null);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature,
                exceptions);

        if (mv == null) {
            return null;
        }

        //TODO  delete old init function no code
        if (name.equals("<init>")) {
            return new InitClassFieldsMethodAdapter(mv, icodesFields, enhancedName);
        }

        MethodDefine md = null;
        for (MethodProxyDefine mpd : mpds) {
            md = mpd.getMethodDefine();
            if (md.getName().equals(name)) {
                if (md.getDesc().equals(desc)) {
                    return new AddMethodCodeAdapter(mv, mpd, enhancedName);
                }
            }
        }

        return mv;
    }

//	public void visit(final int version, final int access, final String name,
//			final String signature, final String superName,
//			final String[] interfaces) {
////		String enhancedSuperName = name; // 改变父类，
//		super.visit(version, access, enhancedName, signature,
//				name, interfaces);
//	}
}
