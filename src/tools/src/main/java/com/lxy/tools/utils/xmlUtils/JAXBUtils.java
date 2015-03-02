package com.lxy.tools.utils.xmlUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

public class JAXBUtils {
    private JAXBUtils() {

        throw new UnsupportedOperationException();
    }

    public static Object unmarshal(Class<?> clazz, InputStream xmlStream)
            throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller u = jc.createUnmarshaller();

        return u.unmarshal(xmlStream);
    }

    public static void marshal(Class<?> clazz, Object obj,
                               OutputStream stream) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        m.marshal(obj, stream);
    }
}
