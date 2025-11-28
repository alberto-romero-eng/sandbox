package localhost.opencsv.sandbox;

import java.util.Date;

import localhost.opencsv.sandbox.SimpleDateFormatHelper.Pattern;

public class VendorOrder {

	private final static SimpleDateFormatHelper SDFH = new SimpleDateFormatHelper(Pattern.ISO_8601_SECONDS);

	// order_id
	private String orderId;

	// customer_order_id
	private String customerOrderId;

	// date_created
	private Date dateCreated;

	// first_name
	private String firstName;

	// last_name
	private String lastName;

	// company_name
	private String companyName;

	// address_line_1
	private String addressLine1;

	// address_line_2
	private String addressLine2;

	// zip_code
	private String zipCode;

	// city
	private String city;

	// country
	private String country;

	// phone
	private String phone;

	// email
	private String email;

	// shipping_details
	private String shippingDetails;

	// shipping_gln
	private String shippingGln;

	// pos_no
	private Integer posNo;

	// gtin
	private String gtin;

	// vendor_article_number
	private String vendorArticleNumber;

	// product_name
	private String productName;

	// quantity
	private Integer quantity;

	// item_price
	private Double itemPrice;

	// price
	private Double price;

	// delivery_date_min
	private Date deliveryDateMin;

	// delivery_date_max
	private Date deliveryDateMax;


	public static VendorOrder from(String[] a) {
		VendorOrder vo = new VendorOrder();
		vo.setOrderId(a[0]);
		vo.setCustomerOrderId(a[1]);
		try {
			vo.setDateCreated(SDFH.parse(a[2]));
		} catch (Throwable ex) {}
		vo.setFirstName(a[3]);
		vo.setLastName(a[4]);
		vo.setCompanyName(a[5]);
		vo.setAddressLine1(a[6]);
		vo.setAddressLine2(a[7]);
		vo.setZipCode(a[8]);
		vo.setCity(a[9]);
		vo.setCountry(a[10]);
		vo.setPhone(a[11]);
		vo.setEmail(a[12]);
		vo.setShippingDetails(a[13]);
		vo.setShippingGln(a[14]);
		try {
			vo.setPosNo(Integer.parseInt(a[15]));
		} catch (Throwable ex) {}
		vo.setGtin(a[16]);
		vo.setVendorArticleNumber(a[17]);
		vo.setProductName(a[18]);
		try {
			vo.setQuantity(Integer.parseInt(a[19]));
		} catch (Throwable ex) {}
		try {
			vo.setItemPrice(Double.parseDouble(a[20]));
		} catch (Throwable ex) {}
		try {
			vo.setPrice(Double.parseDouble(a[21]));
		} catch (Throwable ex) {}
		try {
			vo.setDeliveryDateMin(SDFH.parse(a[22]));
		} catch (Throwable ex) {}
		try {
			vo.setDeliveryDateMax(SDFH.parse(a[23]));
		} catch (Throwable ex) {}
		return vo;
	}




	/**
	 * 
	 * GETTERS, SETTERS
	 * 
	 * 
	 */
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(String shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public String getShippingGln() {
		return shippingGln;
	}

	public void setShippingGln(String shippingGln) {
		this.shippingGln = shippingGln;
	}

	public Integer getPosNo() {
		return posNo;
	}

	public void setPosNo(Integer posNo) {
		this.posNo = posNo;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getVendorArticleNumber() {
		return vendorArticleNumber;
	}

	public void setVendorArticleNumber(String vendorArticleNumber) {
		this.vendorArticleNumber = vendorArticleNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDeliveryDateMin() {
		return deliveryDateMin;
	}

	public void setDeliveryDateMin(Date deliveryDateMin) {
		this.deliveryDateMin = deliveryDateMin;
	}

	public Date getDeliveryDateMax() {
		return deliveryDateMax;
	}

	public void setDeliveryDateMax(Date deliveryDateMax) {
		this.deliveryDateMax = deliveryDateMax;
	}

}
