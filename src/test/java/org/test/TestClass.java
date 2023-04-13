package org.test;

import org.base.BaseClass;
import org.json.simple.parser.ParseException;
import org.pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestClass extends BaseClass {


    CreateAccount createAccount;
    Account account;
    StorePage storePage;
    CollectionsPage collectionsPage;
    CheckoutPage checkoutPage;
    SignIn signIn;

    @BeforeClass
    public void initializing()
    {
        createAccount = new CreateAccount(driver);
        account = new Account(driver);
        storePage = new StorePage(driver);
        collectionsPage =new CollectionsPage(driver);
        checkoutPage = new CheckoutPage(driver);
        signIn =new SignIn(driver);
    }





    @Test(priority = 1)
    public void creatingAccount() throws IOException, ParseException, InterruptedException {
        createAccount.fillingDetails();
        createAccount.welcomePopup();

    }

    @Test(priority = 2)
    public void buyingProductWithCreditCArd() throws InterruptedException, IOException, ParseException {
        account.shopModule();
        storePage.popUp();
        storePage.categoryImage();
        collectionsPage.products();
        collectionsPage.addToCart();
        checkoutPage.shippingAddress();
        checkoutPage.paymentMethod();
        checkoutPage.payWithCard();
        checkoutPage.logout();
    }

    @Test(priority = 3)
    public void buyingProductWithCreditCardAndCoupon() throws IOException, ParseException, InterruptedException {
        signIn.navigateToUrl();
        signIn.signInWithEmailAndPassword();
        account.shopModule();
        storePage.categoryImage();
        collectionsPage.products();
        collectionsPage.addToCart();
        checkoutPage.shippingAddress();
        checkoutPage.paymentMethod();
        checkoutPage.addingCoupon();
        checkoutPage.payWithCard();
        checkoutPage.logout();


    }






}
