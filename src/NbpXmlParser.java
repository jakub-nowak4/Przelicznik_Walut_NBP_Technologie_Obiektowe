import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NbpXmlParser implements RatesParser {
    @Override
    public List<Currency> parse(String data) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl",
                    true
            );

            var builder = factory.newDocumentBuilder();
            Document document = builder.parse(
                    new ByteArrayInputStream(
                            data.getBytes(
                                    java.nio.charset.Charset.forName("ISO-8859-2")
                            )
                    )
            );

            NodeList positions = document.getElementsByTagName("pozycja");
            List<Currency> currencies = new ArrayList<>(positions.getLength());

            for (int i = 0; i < positions.getLength(); i++) {
                Node position = positions.item(i);
                String name = getText(position, "nazwa_waluty");
                String code = getText(position, "kod_waluty");
                int multiplier = Integer.parseInt(
                        getText(position, "przelicznik")
                );
                BigDecimal averageRate = new BigDecimal(
                        getText(position, "kurs_sredni").replace(',', '.')
                );

                currencies.add(
                        new Currency(name, code, averageRate, multiplier)
                );
            }

            return currencies;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Nie udało się sparsować tabeli kursów NBP",
                    e
            );
        }
    }

    private String getText(Node parent, String tagName) {
        Element element = (Element) parent;
        return element.getElementsByTagName(tagName)
                .item(0)
                .getTextContent()
                .trim();
    }
}
