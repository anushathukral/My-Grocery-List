# Grocery List

## keep track of what you need

- The application will allow users to create a grocery list where they can add and remove items.
They can also save the most used items for next time they go to the store.
- Users including anyone that goes to the store to buy groceries and needs a place to write what they need ahead of
time, so they don't forget anything.
- I use grocery lists all the time because I always forget something if I don't write it down so this application would
be very useful to me.


## User Stories

- As a user, I want to be able to add a store bought item to a grocery list
- As a user, I want to be able to remove a store bought item from a grocery list
- As a user, I want to be able to get the total cost of all items in the list
- As a user, I want to be able to view all items in my grocery list
- As a user, I want to have the option to save the grocery list if I chose
- As a user, I want to have to option to reload my grocery list from exactly where I left off

# Instructions for Grader

- You can add a new grocery item to the grocery list by clicking the "Add Item" button and following the prompts,
entering the name of the item and the price.
- You can remove a grocery item from the grocery list by clicking the "Remove Item" button and typing the 
name of the item you want to remove.
- You can get the total price of all items in the grocery list by clicking "Total Items Price"
- You can locate my visual component on the top right of the screen.
- You can save the state of my application by clicking the "Save Items" button
- You can reload the state of my application by clicking the "Load Items" button

# Phase 4: Task 2
Sample of events printed in console after running, if adding an item, console will print out 
"Grocery item added to the list!", etc.

Goodbye!
Wed Nov 29 20:10:22 PST 2023
Grocery item added to the list!
Wed Nov 29 20:10:24 PST 2023
Grocery item added to the list!
Wed Nov 29 20:10:26 PST 2023
Grocery item added to the list!
Wed Nov 29 20:11:13 PST 2023
Grocery item removed from the list!
Wed Nov 29 20:11:16 PST 2023
Total Price of list calculated!

# Phase 4: Task 3
Now that the GroceryApp isn't being used for printing in the console anymore, I would try and combine 
some of the functions with the Grocery GUI, so I could minimize unnecessary code.

I would want to find another way to add action listeners without having to create a separate function for a button and
then another function for actually doing the action for every action being done.

The console also only prints the event log if the Quit button is clicked, I would like to make the console print out
the event log if the x button is clicked as well.

Overtime, I would improve the design of the GUI by adding different foreground colors from background.
I would also move a few of the buttons to different areas of the main panel and change fonts of the text in the buttons.
I would also fix the display when the application is in full screen as the design doesn't cover the full page.
