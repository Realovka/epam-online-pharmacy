# Alpha pharmacy ordering products

The application is for ordering products from pharmacies of Alfa pharmacy chain.
The application has 4 roles: guest, administrator, customer and pharmacist.
The application has two locales: EN and RU.

### Capabilities of **guest** are:

1. registration<br>
2. sending a verification code to email if the user register as a customer<br>
3. view pages "how to do order", "about us", "faq"<br>

### Capabilities of **administrator** are:

1. verification of new pharmacists and making their status is *active*<br>
2. making status of fired pharmacists is *inactive*<br>
3. if the same pharmacists was hired again, then is an opportunity to make his status *active*<br>
4. updating *login, first name, last name, email, telephone* of active pharmacists<br>
5. addition new pharmacies to the chain<br>
6. updating addresses of pharmacies<br>
7. addition information about new product<br>
8. updating information of products<br>

### Capabilities of **customer** are:

1. search by product name<br>
2. search by international non-proprietary name<br>
3. addition the product to the basket and change quantity of product in the basket<br>
4. choice a pharmacy in the desired city to which an order will be delivered<br>
5. sending the order to the pharmacy<br>

### Capabilities of **pharmacist** are:

1. choice of the pharmacy where he works<br>
2. view orders to be processed<br>
3. selection of the "Prepared" status, if all items from the order are in 
stock and the prices coincide with the pharmacy ones. If you have any questions
about the order, a pharmacy employee should contact the customer by phone and
clarify the details. If the order is confirmed by a pharmacist, the customer receives
an e-mail stating that he can pick up the order before the end of the working day.
If the customer refuses to change the order, the pharmacist
will completely delete the order. If the customer does not pick up the order by 
the end of the working day, the order will be automatically deleted.If the customer
does not pick up the order by the end of the working day, the order will be automatically
deleted.<br>
4. at the time the order is bought out by the customer, the pharmacist clicks
the "Complete" button if the customer paid for the order, or "Delete" if 
the customer canceled the order<br>



![tables_to_git](https://user-images.githubusercontent.com/61760081/135717021-4f48c348-a62d-4461-8b4e-7b3454f29186.jpg)

