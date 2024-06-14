import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class scheme {

    public static void main(String[] args) {
        // Paths to the XML and XSD files
        String xmlFile = "books.xml";
        String xsdFile = "books.xsd";

        // Validate the XML file against the XSD file
        boolean isValid = validateXMLSchema(xsdFile, xmlFile);

        if (isValid) {
            System.out.println("XML document is valid.");
        } else {
            System.out.println("XML document is invalid.");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            // Create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Load a WXS schema, represented by a Schema instance
            Schema schema = factory.newSchema(new File(xsdPath));

            // Create a Validator object, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // Validate the XML document
            validator.validate(new StreamSource(new File(xmlPath)));

            return true;
        } catch (SAXException e) {
            System.out.println("SAX Exception: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}