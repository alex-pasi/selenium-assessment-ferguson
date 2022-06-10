package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.cartPage.CartPage;
import com.build.qa.build.selenium.pageobjects.productPage.ProductPage;
import com.build.qa.build.selenium.pageobjects.searchResultPage.SearchResultPage;
import static com.build.qa.build.selenium.utils.Utils.*;

import org.junit.Assert;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		ProductPage productPage = new ProductPage(driver,wait);

		String expectedBrand = "Moen";
		String expectedId = "m6702bn";

		homePage.searchBar.sendKeys(expectedBrand+" "+expectedId);
		clickElement(homePage.searchButton,wait);

		wait.until(ExpectedConditions.visibilityOf(productPage.productBrand));

		Assert.assertEquals(expectedBrand, productPage.productBrand.getText());
		Assert.assertEquals(expectedId.toUpperCase(), productPage.productId.getText().substring(6));

	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {

		driver.get(getConfiguration("BATHROOMSINKSPAGE"));
		SearchResultPage searchResultPage = new SearchResultPage(driver,wait);

		String expectedTitle = searchResultPage.secondElementLink.getAttribute("title");
		String expectedId = searchResultPage.secondElementId.getText();

		clickElement(searchResultPage.secondElementAddToCartButton,wait);
		clickElement(searchResultPage.confirmAddToCartButton,wait);
		clickElement(searchResultPage.viewCartButton,wait);

		CartPage cartPage = new CartPage(driver,wait);

		wait.until(ExpectedConditions.visibilityOf(cartPage.productTitle));

		Assert.assertEquals(expectedTitle,cartPage.productTitle.getText());
		Assert.assertEquals(expectedId,cartPage.productId.getText());
	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver,wait);
		SearchResultPage searchResultPage = new SearchResultPage(driver,wait);
		CartPage cartPage = new CartPage(driver,wait);

		homePage.searchBar.sendKeys("Moen");
		clickElement(homePage.searchButton,wait);

		clickElement(searchResultPage.secondElementAddToCartButton,wait);
		clickElement(searchResultPage.confirmAddToCartButton,wait);

		clickElement(searchResultPage.firstElementAddToCartButton,wait);
		clickElement(searchResultPage.confirmAddToCartButton,wait);
		clickElement(searchResultPage.viewCartButton,wait);

		//saving all initial product prices
		List<Double> initialAllProductPrice = cartPage.getAllProductPrice(driver);

		Double initialFirstProductPrice = initialAllProductPrice.get(0);
		Double initialSecondProductPrice = initialAllProductPrice.get(1);

		//updating product quantity
		cartPage.updateQuantityByIndex(driver,1,"2");
		cartPage.updateQuantityByIndex(driver,2,"2");

		wait.until(ExpectedConditions.textToBePresentInElement
				(cartPage.subtotalPrice,"$"+((initialFirstProductPrice*2)+(initialSecondProductPrice*2))));

		//saving all current product prices
		List<Double> current = cartPage.getAllProductPrice(driver);

		//saving expected prices
		Double expectedFirstProductPrice = initialFirstProductPrice*2;
		Double expectedSecondProductPrice = initialSecondProductPrice*2;
		Double expectedSubtotalPrice = expectedFirstProductPrice+expectedSecondProductPrice;

		//asserting that all prices are as expected
		Assert.assertEquals(expectedFirstProductPrice,current.get(0));
		Assert.assertEquals(expectedSecondProductPrice,current.get(1));
		Assert.assertEquals("$"+expectedSubtotalPrice,cartPage.subtotalPrice.getText());

	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver,wait);
		SearchResultPage searchResultPage = new SearchResultPage(driver,wait);

		clickElement(homePage.bathroomSinkCategory,wait);

		int initialTotalResults = Integer.parseInt(searchResultPage.totalResults.getAttribute("data-total-record"));

		wait.until(ExpectedConditions.visibilityOf(searchResultPage.noHandleFilter));
		searchResultPage.noHandleFilter.click();

		//asserting if first filter is selected
		wait.until(ExpectedConditions.visibilityOf(searchResultPage.noHandleFilterOption));
		Assert.assertEquals("fcheckbox fchecked",searchResultPage.noHandleFilter.getAttribute("class"));

		//asserting that first filter narrowing product count
		int currentTotalResults = Integer.parseInt(searchResultPage.totalResults.getAttribute("data-total-record"));
		Assert.assertTrue(initialTotalResults>currentTotalResults);

		searchResultPage.electronicFilter.click();

		//asserting if second filter is selected
		wait.until(ExpectedConditions.visibilityOf(searchResultPage.electronicFilterOption));
		Assert.assertEquals("fcheckbox fchecked",searchResultPage.electronicFilter.getAttribute("class"));

		//asserting that second filter narrowing product count
		int currentTotalResults2 = Integer.parseInt(searchResultPage.totalResults.getAttribute("data-total-record"));
		Assert.assertTrue(currentTotalResults>currentTotalResults2);

		//asserting that total displayed elements on the page same as total result number
		Assert.assertEquals(currentTotalResults2,searchResultPage.allProducts.size());

	}
}
