package task5;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProgrammingBook
{
    private final SimpleStringProperty m_language;
    private final SimpleStringProperty m_author;
    private final SimpleIntegerProperty m_year;

    public ProgrammingBook(String language, String author, int year)
    {
        m_language = new SimpleStringProperty(language);
        m_author = new SimpleStringProperty(author);
        m_year = new SimpleIntegerProperty(year);
    }

    public int getYear() {
        return m_year.get();
    }

    public void setYear(int year) {
        m_year.set(year);
    }

    public String getAuthor() {
        return m_author.get();
    }

    public void setAuthor(String author) {
        m_author.set(author);
    }

    public String getLanguage() {
        return m_language.get();
    }

    public void setLanguage(String language) {
        m_language.set(language);
    }

    public SimpleStringProperty languageProperty() { return m_language; }

    public SimpleStringProperty authorProperty() { return m_author; }

    public SimpleIntegerProperty yearProperty() { return m_year; }


    @Override
    public String toString() {
        return "ProgrammingBook{" +
                "language='" + m_language + '\'' +
                ", author='" + m_author + '\'' +
                ", year=" + m_year +
                '}';
    }

}
