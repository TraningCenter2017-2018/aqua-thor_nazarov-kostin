package parsers.sax;

import model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parsers.OceanParamsReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SaxOceanParamsReader extends DefaultHandler implements OceanParamsReader {

    private OceanParameters oceanParameters;
    private Flow flow;
    private Vector sizeVector;
    private Vector directionVector;
    private OceanType oceanType;
    private FishParameters agrFishParams;
    private FishParameters pasFishParams;
    private String charactersIdentifier;
    private String coordsIdentifier;
    private String fishStrategyIdentifier;
    private StringBuffer oceanTypeBuffer;
    private StringBuffer sizeXBuffer;
    private StringBuffer sizeYBuffer;
    private StringBuffer directionXBuffer;
    private StringBuffer directionYBuffer;
    private StringBuffer strengthBuffer;
    private StringBuffer agrCountBuffer;
    private StringBuffer pasCountBuffer;
    private StringBuffer pasLifeTimeBuffer;
    private StringBuffer pasReproductionPeriodBuffer;
    private StringBuffer pasSmellSenseDistanceBuffer;
    private StringBuffer pasStarvationTimeBuffer;
    private StringBuffer agrLifeTimeBuffer;
    private StringBuffer agrReproductionPeriodBuffer;
    private StringBuffer agrSmellSenseDistanceBuffer;
    private StringBuffer agrStarvationTimeBuffer;


    public OceanParameters read(InputStream inputStream) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, this);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return oceanParameters;
    }


    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attrs) throws SAXException {

        switch (qualifiedName) {
            case "oceanParameters":
                oceanParameters = new OceanParameters();
                break;
            case "oceanType":
                charactersIdentifier = "oceanType";
                oceanTypeBuffer = new StringBuffer();
                break;
            case "oceanSize":
                sizeVector = new Vector();
                coordsIdentifier = "oceanSize";
                break;
            case "x":
                switch (coordsIdentifier) {
                    case "oceanSize":
                        charactersIdentifier = "sizeX";
                        sizeXBuffer = new StringBuffer();
                        break;
                    case "direction":
                        charactersIdentifier = "directionX";
                        directionXBuffer = new StringBuffer();
                        break;
                }
                break;
            case "y":
                switch (coordsIdentifier) {
                    case "oceanSize":
                        charactersIdentifier = "sizeY";
                        sizeYBuffer = new StringBuffer();
                        break;
                    case "direction":
                        charactersIdentifier = "directionY";
                        directionYBuffer = new StringBuffer();
                        break;
                }
                break;
            case "flow":
                flow = new Flow();
                break;
            case "direction":
                directionVector = new Vector();
                coordsIdentifier = "direction";
                break;
            case "strength":
                charactersIdentifier = "strength";
                strengthBuffer = new StringBuffer();
                break;
            case "passiveFishCount":
                charactersIdentifier = "pasCount";
                pasCountBuffer = new StringBuffer();
                break;
            case "aggressiveFishCount":
                charactersIdentifier = "agrCount";
                agrCountBuffer = new StringBuffer();
                break;
            case "passiveFishParameters":
                pasFishParams = new FishParameters();
                fishStrategyIdentifier = "pas";
                break;
            case "lifeTimeTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "pasLifeTime";
                        pasLifeTimeBuffer = new StringBuffer();
                        break;
                    case "agr":
                        charactersIdentifier = "agrLifeTime";
                        agrLifeTimeBuffer = new StringBuffer();
                        break;
                }
                break;
            case "reproductionPeriodTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "pasReproductionPeriod";
                        pasReproductionPeriodBuffer = new StringBuffer();
                        break;
                    case "agr":
                        charactersIdentifier = "agrReproductionPeriod";
                        agrReproductionPeriodBuffer = new StringBuffer();
                        break;
                }
                break;
            case "smellSenseDistance":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "pasSmellSense";
                        pasSmellSenseDistanceBuffer = new StringBuffer();
                        break;
                    case "agr":
                        charactersIdentifier = "agrSmellSense";
                        agrSmellSenseDistanceBuffer = new StringBuffer();
                        break;
                }
                break;
            case "starvationTimeTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "pasStarvation";
                        pasStarvationTimeBuffer = new StringBuffer();
                        break;
                    case "agr":
                        charactersIdentifier = "agrStarvation";
                        agrStarvationTimeBuffer = new StringBuffer();
                        break;
                }
                break;
            case "aggressiveFishParameters":
                agrFishParams = new FishParameters();
                fishStrategyIdentifier = "agr";
                break;

        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (charactersIdentifier) {
            case "oceanType":
                oceanTypeBuffer.append(ch, start, length);
                break;
            case "sizeX":
                sizeXBuffer.append(ch, start, length);
                break;
            case "sizeY":
                sizeYBuffer.append(ch, start, length);
                break;
            case "directionX":
                directionXBuffer.append(ch, start, length);
                break;
            case "directionY":
                directionYBuffer.append(ch, start, length);
                break;
            case "strength":
                strengthBuffer.append(ch, start, length);
                break;
            case "pasCount":
                pasCountBuffer.append(ch, start, length);
                break;
            case "agrCount":
                agrCountBuffer.append(ch, start, length);
                break;
            case "pasLifeTime":
                pasLifeTimeBuffer.append(ch, start, length);
                break;
            case "agrLifeTime":
                agrLifeTimeBuffer.append(ch, start, length);
                break;
            case "pasReproductionPeriod":
                pasReproductionPeriodBuffer.append(ch, start, length);
                break;
            case "agrReproductionPeriod":
                agrReproductionPeriodBuffer.append(ch, start, length);
                break;
            case "pasSmellSense":
                pasSmellSenseDistanceBuffer.append(ch, start, length);
                break;
            case "agrSmellSense":
                agrSmellSenseDistanceBuffer.append(ch, start, length);
                break;
            case "pasStarvation":
                pasStarvationTimeBuffer.append(ch, start, length);
                break;
            case "agrStarvation":
                agrStarvationTimeBuffer.append(ch, start, length);
                break;

        }
    }

    @Override
    public void endElement(String nameURI, String localName, String qualifiedName) throws SAXException {

        switch (qualifiedName) {

            case "oceanParameters":
                //  oceanParameters = new OceanParameters();
                break;
            case "oceanType":
                charactersIdentifier = "";
                switch (oceanTypeBuffer.toString()) {
                    case "BORDERED":
                        oceanType = oceanType.BORDERED;
                        break;
                    case "BORDERLESS":
                        oceanType = oceanType.BORDERLESS;
                        break;
                }
                oceanParameters.setOceanType(oceanType);
                break;
            case "oceanSize":
                coordsIdentifier = "";
                oceanParameters.setOceanSize(sizeVector);
                break;
            case "x":
                switch (coordsIdentifier) {
                    case "oceanSize":
                        charactersIdentifier = "";
                        sizeVector.setX(Integer.valueOf(sizeXBuffer.toString()));
                        break;
                    case "direction":
                        charactersIdentifier = "";
                        directionVector.setX(Integer.valueOf(directionXBuffer.toString()));
                        break;
                }
                break;
            case "y":
                switch (coordsIdentifier) {
                    case "oceanSize":
                        charactersIdentifier = "";
                        sizeVector.setY(Integer.valueOf(sizeYBuffer.toString()));
                        break;
                    case "direction":
                        charactersIdentifier = "";
                        directionVector.setY(Integer.valueOf(directionYBuffer.toString()));
                        break;
                }
                break;
            case "flow":
                oceanParameters.setFlow(flow);
                break;
            case "direction":
                coordsIdentifier = "";
                flow.setDirection(directionVector);
                break;
            case "strength":
                charactersIdentifier = "";
                flow.setStrength(Integer.valueOf(strengthBuffer.toString()));
                break;
            case "passiveFishCount":
                charactersIdentifier = "";
                oceanParameters.setPassiveFishCount(Integer.valueOf(pasCountBuffer.toString()));
                break;
            case "aggressiveFishCount":
                charactersIdentifier = "";
                oceanParameters.setAggressiveFishCount(Integer.valueOf(agrCountBuffer.toString()));
                break;
            case "passiveFishParameters":
                fishStrategyIdentifier = "";
                oceanParameters.setPassiveFishParameters(pasFishParams);
                break;
            case "lifeTimeTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "";
                        pasFishParams.setLifeTimeTicks(Integer.valueOf(pasLifeTimeBuffer.toString()));
                        break;
                    case "agr":
                        charactersIdentifier = "";
                        agrFishParams.setLifeTimeTicks(Integer.valueOf(agrLifeTimeBuffer.toString()));
                        break;
                }
                break;
            case "reproductionPeriodTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "";
                        pasFishParams.setReproductionPeriodTicks(Integer.valueOf(pasReproductionPeriodBuffer.toString()));
                        break;
                    case "agr":
                        charactersIdentifier = "";
                        agrFishParams.setReproductionPeriodTicks(Integer.valueOf(agrReproductionPeriodBuffer.toString()));
                        break;
                }
                break;
            case "smellSenseDistance":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "";
                        pasFishParams.setSmellSenseDistance(Integer.valueOf(pasSmellSenseDistanceBuffer.toString()));
                        break;
                    case "agr":
                        charactersIdentifier = "";
                        agrFishParams.setSmellSenseDistance(Integer.valueOf(agrSmellSenseDistanceBuffer.toString()));
                        break;
                }
                break;
            case "starvationTimeTicks":
                switch (fishStrategyIdentifier) {
                    case "pas":
                        charactersIdentifier = "";
                        pasFishParams.setStarvationTimeTicks(Integer.valueOf(pasStarvationTimeBuffer.toString()));
                        break;
                    case "agr":
                        charactersIdentifier = "";
                        agrFishParams.setStarvationTimeTicks(Integer.valueOf(agrStarvationTimeBuffer.toString()));
                        break;
                }
                break;
            case "aggressiveFishParameters":
                fishStrategyIdentifier = "";
                oceanParameters.setAggressiveFishParameters(agrFishParams);
                break;

        }

    }

}
