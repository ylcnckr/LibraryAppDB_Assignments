package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BorrowedBookStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBarrowBooks;
    String expectedBarrowBooks;
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {

        loginPage.login(librarian);
        BrowserUtil.waitFor(3);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        DB_Util.createConnection();

        String query = "select count(*) from book_borrow\n" +
                "where is_returned = 0";
        DB_Util.runQuery(query);
        expectedBarrowBooks = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBarrowBooks = " + expectedBarrowBooks);

        actualBarrowBooks = dashBoardPage.borrowedBooksNumber.getText();

        BrowserUtil.waitFor(3);
        System.out.println("actualBarrowBooks = " + actualBarrowBooks);


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        Assert.assertEquals(expectedBarrowBooks,actualBarrowBooks);
    }

}
