# inventoryApp
CS 360 Term Project - Creating a mobile inventory application for Android

Initial Commit has the following elements:

A Text Greeting

Dynamically enabled/disabled "Say Hello" Button

A "Hello, " + nameText Greeting

SECOND MERGE CONTAINS FINAL PROJECT  - INVENTORY MANAGER

The requirements and goal of this project was to create an inventory application for Android
that allowed different users to create different inventories. I created a login screen to authorize users and allow
them to create different inventory databases for their own personal inventory. The login screen was
necessary to insert a user into the user database and ensure that their inventory data was stored correctly.
I created a Welcome screen that allowed users to enable SMS notifications, which would alert the user if 
an item was at zero quantity. I also created a recyclerView of the inventory in list form to 
display quantity of an item and the functionality to edit or delete items. This screen also included
the ability to delete all items. All of this gave user's complete control over their inventory
and allowed them to visualize their full inventory on one screen.

This was my first attempt at developing an app from start to finish, and in my opinion, it was an okay 
first try. I did not get the RecyclerView to function, and I need to do some code refactoring in my adapter
and holder classes. However, I stuck to my initial design plan as closely as possible by creating some optional
screens that gave the user a bit more control and ease of use than just showing them an inventory screen
with a bunch of buttons. I wrote JUnit tests to improve login functionality as well as JUnit tests that tested
my SQLite database statements. I also debugged as I went along to ensure that every change made was a positive one.

In the middle of developing this app, Android Studios came out with a new IDE update, and I decided to upgrade 
the software. This proved to be a big mistake, as most of the gradle files switched to gradle.ks files and 
caused my entire project to be refactored. I ended up creating a new project and starting over, copying and 
pasting a lot of code. This was a lesson in version control for me, as I should have uploaded my entire project
to GitHub much more often than I did. I fully intended to keep uploading changes as I went, but got sloppy in this
regard. I also didn't apply much of a strategy beyond designing the screens and then just going for it as 
far as functionality goes. I jumped in head first and soon found myself drowning in information. Trying many different
approaches within one project is not the way to go, and in the future I will adhere to a more planned, strategic development
plan and approach. 

I think for a first try, I tried too many things and eventually was not clear on how to make it all come together. I am 
going to continue to work on this project to make sure my list of inventory is clearly visible to the user. I may
have to switch my RecyclerView approach and look more into details and fragments, but I figured with an inventory app,
RecyclerView was the way to go. I need to read more and fully understand how the adapter, holder, and recycler work 
together to display information from a database. 

Overall, I value everything I learned in this class, it was a great first step, but I feel like I have a lot of 
improvement to go, and I hope to chronicle that progress here on GitHub until I am ready to publish this app 
(my first app) on the Google Play Store!

