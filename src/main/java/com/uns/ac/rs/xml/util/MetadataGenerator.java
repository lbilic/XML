package com.uns.ac.rs.xml.util;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.domain.enums.UserType;

@Component
public class MetadataGenerator {

    public void addUserMetadata(Document document) {
        NodeList elements = document.getFirstChild().getChildNodes();
        Element element;
        int count = 0;
        for (int i = 0; i < elements.getLength() && count < 3; i++) {
            try {
                element = (Element) elements.item(i);
                switch (element.getLocalName()) {
                    case "name":
                        element.setAttribute("property", "voc:name");
                        element.setAttribute("datatype", "xs:string");
                        ++count;
                        break;
                    case "surname":
                        element.setAttribute("property", "voc:surname");
                        element.setAttribute("datatype", "xs:string");
                        ++count;
                        break;
                    case "jmbg":
                        element.setAttribute("property", "voc:jmbg");
                        element.setAttribute("datatype", "xs:string");
                        ++count;
                        break;
                }
            } catch (Exception ignored) {
            }

        }
    }

    public void addPersonMetadata(Document document, UserType userType, String id) {
        NodeList elements = document.getFirstChild().getChildNodes();
        Element root = ((Element) document.getFirstChild());
        Element element;
        int count = 0;
        switch (userType) {
            case DOCTOR:
                root.setAttribute("about", id);
                root.setAttribute("typeof", "voc:Doctor");
                for (int i = 0; i < elements.getLength() && count < 2; i++) {
                    try {
                        element = (Element) elements.item(i);
                        switch (element.getLocalName()) {
                            case "type":
                                element.setAttribute("property", "voc:doctorType");
                                element.setAttribute("datatype", "xs:string");
                                ++count;
                                break;
                            case "oblast_zastite":
                                element.setAttribute("property", "voc:oblastZastite");
                                element.setAttribute("datatype", "xs:string");
                                ++count;
                                break;
                            case "number_pacijenata":
                                element.setAttribute("property", "voc:numberPacijenata");
                                element.setAttribute("datatype", "xs:integer");
                                ++count;
                                break;
                        }
                    } catch (Exception ignored) {
                    }
                }
                break;
            case PATIENT:
                root.setAttribute("about", id);
                root.setAttribute("typeof", "voc:ZdrastveniKarton");
                for (int i = 0; i < elements.getLength() && count < 2; i++) {
                    try {
                        element = (Element) elements.item(i);
                        switch (element.getLocalName()) {
                            case "insured_person":
                                this.addMetadataPacijentu(element.getChildNodes(), id);
                                ++count;
                                break;
                            case "chosen_doctr":
                                String doctorId = element.getAttributes().item(0).getNodeValue();
                                element.setAttribute("rel", "voc:odabraniDoctor");
                                element.setAttribute("href", doctorId);
                                ++count;
                                break;
                        }
                    } catch (Exception ignored) {
                    }
                }
                break;
        }
    }

    private void addMetadataPacijentu(NodeList elements, String id) {
        String[] urls = this.generateURL(elements);
        String addressURL = urls[0];
        String municipalityURL = urls[1];
        int counter = 0;
        Element element;
        for (int i = 0; i < elements.getLength() && counter < 6; i++) {
            try {
                element = (Element) elements.item(i);
                switch (element.getLocalName()) {
                    case "name":
                        element.setAttribute("about", id);
                        element.setAttribute("property", "voc:name");
                        element.setAttribute("datatype", "xs:string");
                        ++counter;
                        break;
                    case "surname":
                        element.setAttribute("about", id);
                        element.setAttribute("property", "voc:surname");
                        element.setAttribute("datatype", "xs:string");
                        ++counter;
                        break;
                    case "jmbg":
                        element.setAttribute("about", id);
                        element.setAttribute("property", "voc:jmbg");
                        element.setAttribute("datatype", "xs:string");
                        ++counter;
                        break;
                    case "sex":
                        element.setAttribute("about", id);
                        element.setAttribute("property", "voc:sex");
                        element.setAttribute("datatype", "xs:string");
                        ++counter;
                        break;
                    case "birth_date":
                        element.setAttribute("about", id);
                        element.setAttribute("property", "voc:birth_date");
                        element.setAttribute("datatype", "xs:date");
                        ++counter;
                        break;
                    case "address":
                        element.setAttribute("about", id);
                        element.setAttribute("rel", "voc:stanuje");
                        element.setAttribute("href", addressURL);
                        ++counter;
                        NodeList address = element.getChildNodes();
                        Element el;
                        int addressCounter = 0;
                        for (int j = 0; j < address.getLength() && addressCounter < 5; j++) {
                            try {
                                el = (Element) address.item(j);
                                switch (el.getLocalName()) {
                                    case "street":
                                        el.setAttribute("about", addressURL);
                                        el.setAttribute("property", "voc:street");
                                        el.setAttribute("datatype", "xs:string");
                                        ++addressCounter;
                                        break;
                                    case "number":
                                        el.setAttribute("about", addressURL);
                                        el.setAttribute("property", "voc:number");
                                        el.setAttribute("datatype", "xs:integer");
                                        ++addressCounter;
                                        break;
                                    case "appartment_number":
                                        el.setAttribute("about", addressURL);
                                        el.setAttribute("property", "voc:numberStana");
                                        el.setAttribute("datatype", "xs:integer");
                                        ++addressCounter;
                                        break;
                                    case "town":
                                        el.setAttribute("about", addressURL);
                                        el.setAttribute("property", "voc:town");
                                        el.setAttribute("datatype", "xs:string");
                                        ++addressCounter;
                                        break;
                                    case "municipality":
                                        ++addressCounter;
                                        el.setAttribute("about", addressURL);
                                        el.setAttribute("rel", "voc:municipality");
                                        el.setAttribute("href", municipalityURL);
                                        NodeList municipality = el.getChildNodes();
                                        Element e;
                                        int municipalityCounter = 0;
                                        for (int k = 0; k < municipality.getLength() && municipalityCounter < 2; k++) {
                                            try {
                                                e = (Element) municipality.item(k);
                                                switch (e.getLocalName()) {
                                                    case "name":
                                                        e.setAttribute("about", municipalityURL);
                                                        e.setAttribute("property", "voc:nameOpstine");
                                                        e.setAttribute("datatype", "xs:string");
                                                        ++municipalityCounter;
                                                        break;
                                                    case "zip_code":
                                                        e.setAttribute("about", municipalityURL);
                                                        e.setAttribute("property", "voc:zipCode");
                                                        e.setAttribute("datatype", "xs:integer");
                                                        ++municipalityCounter;
                                                        break;
                                                }
                                            } catch (Exception ignored) {
                                            }
                                        }
                                        break;
                                }
                            } catch (Exception ignored) {
                            }

                        }
                        break;
                }
            } catch (Exception ignored) {
            }

        }
    }


    public NodeList addDrugMetadata(NodeList elements, String id) {
        Element element;
        int count = 0;
        for (int i = 0; i < elements.getLength() && count < 1; i++) {
            if (!elements.item(i).getTextContent().equals("\n")) {
                try {
                    element = (Element) elements.item(i);
                    if ("name".equals(element.getTagName().split(":")[1])) {
                        element.setAttribute("property", "voc:name");
                        element.setAttribute("datatype", "xs:string");
                        ++count;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return elements;
    }

    public NodeList addReportMetadata(NodeList elements, String id) {
        Element element;
        int counter = 0;
        for (int i = 0; i < elements.getLength() && counter < 3; i++) {
            if (!elements.item(i).getTextContent().equals("\n")) {
                try {
                    element = (Element) elements.item(i);
                    switch (element.getTagName().split(":")[1]) {
                        case "date":
                            Element parent = (Element) element.getParentNode();
                            parent.setAttribute("about", id);
                            element.setAttribute("property", "voc:date");
                            element.setAttribute("datatype", "xs:date");
                            ++counter;
                            break;
                        case "insured_person":
                            String liceId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:insuredPerson");
                            element.setAttribute("href", liceId);
                            ++counter;
                            break;
                        case "doctor":
                            String doctorId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:doctor");
                            element.setAttribute("href", doctorId);
                            ++counter;
                            break;
                        case "anamnesis":
                            NodeList links = element.getChildNodes();
                            Element link;
                            String dokId;
                            for (int j = 0; j < links.getLength(); j++) {
                                try {
                                    if (!links.item(j).getTextContent().equals("\n")) {
                                        link = (Element) links.item(j);
                                        dokId = link.getAttributes().item(0).getNodeValue();
                                        link.setAttribute("rel", "voc:referenceToDocument");
                                        link.setAttribute("href", dokId);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            break;
                        case "therapy":
                            NodeList lnk = element.getChildNodes();
                            Element lk;
                            String dId;
                            for (int j = 0; j < lnk.getLength(); j++) {
                                try {
                                    if (!lnk.item(j).getTextContent().equals("\n")) {
                                        lk = (Element) lnk.item(j);
                                        dId = lk.getAttributes().item(0).getNodeValue();
                                        lk.setAttribute("rel", "voc:referenceToDocument");
                                        lk.setAttribute("href", dId);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            break;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return elements;
    }


    public NodeList addReferralMetadata(NodeList elements, String id) {
        Element element;
        int counter = 0;
        for (int i = 0; i < elements.getLength() && counter < 5; i++) {
            if (!elements.item(i).getTextContent().equals("\n")) {
                try {
                    element = (Element) elements.item(i);
                    switch (element.getTagName().split(":")[1]) {
                        case "date":
                            Element parent = (Element) element.getParentNode();
                            parent.setAttribute("about", id);
                            element.setAttribute("property", "voc:date");
                            element.setAttribute("datatype", "xs:date");
                            ++counter;
                            break;
                        case "insured_person":
                            String liceId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:insuredPerson");
                            element.setAttribute("href", liceId);
                            ++counter;
                            break;
                        case "doctor":
                            String doctorId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:doctor");
                            element.setAttribute("href", doctorId);
                            ++counter;
                            break;
                        case "specialist":
                            String specId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:specialist");
                            element.setAttribute("href", specId);
                            ++counter;
                            break;
                        case "opinion":
                            NodeList links = element.getChildNodes();
                            Element link;
                            String dokId;
                            for (int j = 0; j < links.getLength(); j++) {
                                try {
                                    if (!links.item(j).getTextContent().equals("\n")) {
                                        link = (Element) links.item(j);
                                        dokId = link.getAttributes().item(0).getNodeValue();
                                        link.setAttribute("rel", "voc:referenceToDocument");
                                        link.setAttribute("href", dokId);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            break;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return elements;
    }

    public NodeList addPrescriptionMetadata(NodeList elements, String id) {
        Element element;
        int counter = 0;
        for (int i = 0; i < elements.getLength() && counter < 5; i++) {
            if (!elements.item(i).getTextContent().equals("\n")) {
                try {
                    element = (Element) elements.item(i);
                    switch (element.getTagName().split(":")[1]) {
                        case "date":
                            Element parent = (Element) element.getParentNode();
                            parent.setAttribute("about", id);
                            element.setAttribute("property", "voc:date");
                            element.setAttribute("datatype", "xs:date");
                            ++counter;
                            break;
                        case "insured_person":
                            String liceId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:insuredPerson");
                            element.setAttribute("href", liceId);
                            ++counter;
                            break;
                        case "doctor":
                            String doctorId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:doctor");
                            element.setAttribute("href", doctorId);
                            ++counter;
                            break;
                        case "prescribed_drug":
                            String drugId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:drug");
                            element.setAttribute("href", drugId);
                            ++counter;
                            break;
                        case "description":
                            NodeList links = element.getChildNodes();
                            Element link;
                            String dokId;
                            for (int j = 0; j < links.getLength(); j++) {
                                try {
                                    if (!links.item(j).getTextContent().equals("\n")) {
                                        link = (Element) links.item(j);
                                        dokId = link.getAttributes().item(0).getNodeValue();
                                        link.setAttribute("rel", "voc:referenceToDocument");
                                        link.setAttribute("href", dokId);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            break;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return elements;
    }

    public NodeList addChoiceMetadata(NodeList elements, String id) {
        Element element;
        int counter = 0;
        for (int i = 0; i < elements.getLength() && counter < 5; i++) {
            if (!elements.item(i).getTextContent().equals("\n")) {
                try {
                    element = (Element) elements.item(i);
                    switch (element.getTagName().split(":")[1]) {
                        case "date":
                            Element parent = (Element) element.getParentNode();
                            if (parent.hasAttribute("xmlns:akc")) {
                                parent.removeAttribute("xmlns:akc");
                            }
                            parent.setAttribute("about", id);
                            element.setAttribute("property", "voc:date");
                            element.setAttribute("datatype", "xs:date");
                            ++counter;
                            break;
                        case "insured_person":
                            String liceId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:insuredPerson");
                            element.setAttribute("href", liceId);
                            ++counter;
                            break;
                        case "doctor":
                            String doctorId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:doctor");
                            element.setAttribute("href", doctorId);
                            ++counter;
                            break;
                        case "previous_doctor":
                            String lkId = element.getAttributes().item(0).getNodeValue();
                            element.setAttribute("rel", "voc:previousDoctor");
                            element.setAttribute("href", lkId);
                            ++counter;
                            break;
                        case "razlog_promene":
                            element.setAttribute("property", "voc:reasonForChange");
                            element.setAttribute("datatype", "xs:string");
                            ++counter;
                            break;
                    }
                } catch (Exception ignored) {
                }

            }
        }
        return elements;
    }



    private String[] generateURL(NodeList elements) {
        StringBuilder sba = new StringBuilder("http://www.zis.rs/address/");
        StringBuilder sbo = new StringBuilder("http://www.zis.rs/municipality/");
        for (int i = 0; i < elements.getLength(); i++) {
            Element element;
            try {
                element = (Element) elements.item(i);
                if (element.getLocalName().equals("address")) {
                    NodeList address = element.getChildNodes();
                    int addressCounter = 0;
                    for (int j = 0; j < address.getLength() && addressCounter < 5; j++) {
                        try {
                            switch (address.item(j).getLocalName()) {
                                case "street":
                                    sba.append(address.item(j).getTextContent());
                                    sba.append("/");
                                    ++addressCounter;
                                    break;
                                case "number":
                                    sba.append(address.item(j).getTextContent());
                                    sba.append("/");
                                    ++addressCounter;
                                    break;
                                case "appartment_number":
                                    sba.append(address.item(j).getTextContent());
                                    sba.append("/");
                                    ++addressCounter;
                                    break;
                                case "town":
                                    sba.append(address.item(j).getTextContent());
                                    ++addressCounter;
                                    break;
                                case "municipality":
                                    ++addressCounter;
                                    NodeList municipality = address.item(j).getChildNodes();
                                    int municipalityCounter = 0;
                                    for (int k = 0; k < municipality.getLength() && municipalityCounter < 2; k++) {
                                        try {
                                            switch (municipality.item(k).getLocalName()) {
                                                case "name":
                                                    sbo.append(municipality.item(k).getTextContent());
                                                    sbo.append("/");
                                                    ++municipalityCounter;
                                                    break;
                                                case "zip_code":
                                                    sbo.append(municipality.item(k).getTextContent());
                                                    ++municipalityCounter;
                                                    break;
                                            }
                                        } catch (Exception ignored) {
                                        }
                                    }
                                    break;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return new String[]{sba.toString(), sbo.toString()};
    }


}
