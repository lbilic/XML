package com.uns.ac.rs.xml.services.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.ChoiceXMLRepository;
import com.uns.ac.rs.xml.repository.ReportXMLRepository;
import com.uns.ac.rs.xml.repository.ReferralXMLRepository;
import com.uns.ac.rs.xml.util.transformators.PDFTransformator;

import java.io.*;

@Service
public class TransformationsService {

    @Autowired
    private PDFTransformator pdfTransformator;

    @Autowired
    private ReferralXMLRepository prescriptionXMLRepository;

    @Autowired
    private ReferralXMLRepository referralXMLRepository;

    @Autowired
    private ChoiceXMLRepository choiceXMLRepository;

    @Autowired
    private ReportXMLRepository reportXMLRepository;

    public String transformPrescription(Long id, String xmlPath, String xslPath, String htmlPath, String pdfPath) {
        String xml = prescriptionXMLRepository.findById("http://www.zis.rs/prescriptions/id" + id);
        try {
            File newFile = new File("src/main/resources/generated/prescriptions.xml");
            newFile.createNewFile();

            BufferedWriter bis = new BufferedWriter(new PrintWriter(newFile));
            bis.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<?xml-stylesheet type=\"text/xsl\" href=\"src/main/resources/xsl/prescriptions.xsl\"?>\n" +
                    "<prescriptions:prescriptions\n" +
                    " xmlns:prescription=\"http://www.zis.rs/schemes/prescription\"\n" +
                    " xmlns:prescriptions=\"http://www.zis.rs/schemes/prescriptions\"\n" +
                    ">\n");
            bis.append(xml);
            bis.append("\n</prescriptions:prescriptions>");
            bis.close();
            PDFTransformator.generateHTML(xmlPath, xslPath, htmlPath);
            PDFTransformator.generatePDF(pdfPath, htmlPath);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public String transformReferral(Long id, String xmlPath, String xslPath, String htmlPath, String pdfPath){
        String xml = referralXMLRepository.findById("http://www.zis.rs/referrals/id" + id);
        try{
            File newFile = new File("src/main/resources/generated/referral.xml");
            newFile.createNewFile();
            BufferedWriter bis = new BufferedWriter(new PrintWriter(newFile));
            bis.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<?xml-stylesheet type=\"text/xsl\" href=\"src/main/resources/xsl/referrals.xsl\"?>\n" +
                    "<referrals:referrals\n" +
                    " xmlns:referral=\"http://www.zis.rs/schemes/referral\"\n" +
                    " xmlns:referrals=\"http://www.zis.rs/schemes/referrals\"\n" +
                    ">\n");
            bis.append(xml);
            bis.append("\n</referrals:referrals>");
            bis.close();
            PDFTransformator.generateHTML(xmlPath, xslPath, htmlPath);
            PDFTransformator.generatePDF(pdfPath, htmlPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return xml;
    }


    public String transformChoice(Long id, String xmlPath, String xslPath, String htmlPath, String pdfPath) {
        String xml = choiceXMLRepository.findById("http://www.zis.rs/choices/id" + id);
        try {
            File newFile = new File("src/main/resources/generated/choices.xml");
            newFile.createNewFile();

            BufferedWriter bis = new BufferedWriter(new PrintWriter(newFile));
            bis.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<?xml-stylesheet type=\"text/xsl\" href=\"src/main/resources/xsl/choices.xsl\"?>\n" +
                    "<choices:choices\n" +
                    " xmlns:choice=\"http://www.zis.rs/schemes/choice\"\n" +
                    " xmlns:choices=\"http://www.zis.rs/schemes/choices\"\n" +
                    ">\n");
            bis.append(xml);
            bis.append("\n</choices:choices>");
            bis.close();
            PDFTransformator.generateHTML(xmlPath, xslPath, htmlPath);
            PDFTransformator.generatePDF(pdfPath, htmlPath);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public String transformReport(Long id, String xmlPath, String xslPath, String htmlPath, String pdfPath) {
        String xml = reportXMLRepository.findById("http://www.zis.rs/reports/id" + id);
        try {
            File newFile = new File("src/main/resources/generated/reports.xml");
            newFile.createNewFile();

            BufferedWriter bis = new BufferedWriter(new PrintWriter(newFile));
            bis.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<?xml-stylesheet type=\"text/xsl\" href=\"src/main/resources/xsl/reports.xsl\"?>\n" +
                    "<reports:reports\n" +
                    " xmlns:report=\"http://www.zis.rs/schemes/report\"\n" +
                    " xmlns:reports=\"http://www.zis.rs/schemes/reports\"\n" +
                    ">\n");
            bis.append(xml);
            bis.append("\n</reports:reports>");
            bis.close();
            PDFTransformator.generateHTML(xmlPath, xslPath, htmlPath);
            PDFTransformator.generatePDF(pdfPath, htmlPath);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return xml;
    }


}
