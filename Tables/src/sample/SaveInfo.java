package sample;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sample.Students;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveInfo {
    private File file;
    public void setFile(File file) {
        this.file = file;
    }
    public void write(ObservableList<Students> listOfStudents){

        List<String> name = new ArrayList<>();
        List<String> surname = new ArrayList<>();
        List<String> group = new ArrayList<>();
        List<String> course = new ArrayList<>();
        List<String> works = new ArrayList<>();
        List<String> allWorks = new ArrayList<>();
        List<String> lang = new ArrayList<>();
        for(int rowNumb = 0; rowNumb<listOfStudents.size(); rowNumb++){
            Students info = listOfStudents.get(rowNumb);
            name.add(info.getName());
            surname.add(info.getSurname());
            group.add(info.getNumberOfGroup());
            course.add(info.getCourse());
            works.add(info.getWorks());
            allWorks.add(info.getTotalWorks());
            lang.add(info.getLangOfProgram());
        }
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element company = document.createElement("students");
            document.appendChild(company);
            for(int i=0; i<listOfStudents.size(); i++) {
                Element fio = document.createElement("FIO");
                Element nameTag = document.createElement("name");
                Element surnameTag = document.createElement("surname");
                Element groupTag = document.createElement("group");
                Element courseTag = document.createElement("course");
                Element worksTag = document.createElement("works");
                Element allWorksTag = document.createElement("allworks");
                Element langTag = document.createElement("language");
                Element student = document.createElement("student");
                nameTag.setTextContent(name.get(i));
                surnameTag.setTextContent(surname.get(i));
                groupTag.setTextContent(group.get(i));
                courseTag.setTextContent(course.get(i));
                worksTag.setTextContent(works.get(i));
                allWorksTag.setTextContent(allWorks.get(i));
                langTag.setTextContent(lang.get(i));
                company.appendChild(student);
                student.appendChild(fio);
                fio.appendChild(nameTag);
                fio.appendChild(surnameTag);
                student.appendChild(groupTag);
                student.appendChild(courseTag);
                student.appendChild(worksTag);
                student.appendChild(allWorksTag);
                student.appendChild(langTag);
            }
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Документ сохранен!");

        }
        catch (Exception ex){ex.printStackTrace();}
    }



}
