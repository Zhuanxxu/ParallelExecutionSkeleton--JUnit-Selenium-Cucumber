package cleanTest.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.BooksPage;


import static java.lang.String.valueOf;
//import static utils.ExcelGenerator.excelSheet;

public class Steps{
    private BooksPage booksPage;


    public Steps(BooksPage booksPage){
        this.booksPage = booksPage;
    }

    @Given("Entro a la pagina de books")
    public void entroALaPaginaDeBooks() throws InterruptedException {
        booksPage.navigate("https://demoqa.com/links");
        Thread.sleep(2000);
        Assertions.assertEquals(2,3);
    }


    @Then("verifico")
    public void verifico() throws InterruptedException {
//        Assertions.assertEquals("Created",booksPage.devolverLinkCreated());
        Thread.sleep(3000);
    }

    @Given("Entro a la pagina de books por segunda vez")
    public void entroALaPaginaDeBooksPorSegundaVez() throws InterruptedException {
        booksPage.navigate("https://demoqa.com/text-box");
        Thread.sleep(2000);
    }

    @Then("verifico otra vez")
    public void verificoOtraVez() throws InterruptedException {
//        Assertions.assertEquals("Full Name",booksPage.devolverFullName());
        Thread.sleep(2000);
    }

}
