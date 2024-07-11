This project is a GUI representation of the first project.

I was asked to do GUI that can:
- add new object
- delete objects (not just from the table, but the actual instance of the object)
- view the objects
- modify the objects
- change it's password
- log out

You need to be able to log in as a member of the "Company", for this example i will log in as Tomek.

His credentials are: **Tomek:123456**

Here's the login page:
![image](https://github.com/etcshado/Projects/assets/146455588/184f6d41-32fc-493b-a184-9f34da50e06a)

Here are all the workers of the company:

![image](https://github.com/etcshado/Projects/assets/146455588/0b1e177f-4efa-4603-9233-fad47e3fe1dd)

You can create a new object:

![image](https://github.com/etcshado/Projects/assets/146455588/bd5364d4-970c-4222-a582-7954572f93b3)

![image](https://github.com/etcshado/Projects/assets/146455588/a798a8e3-020d-4ce8-a584-213b4ca0ff7e)

So with that, i just created a new Brygadzista, with the name of **Test**, surname of **Test**, DOB of current date, Dzial of **Dzial2**, Login of **Test** and a Password of **123**.

Let's see if the system can see him.

![image](https://github.com/etcshado/Projects/assets/146455588/4e3da38d-4367-4ca0-a57d-804f6b25fae9)

It sure can, how about logging as him?

![image](https://github.com/etcshado/Projects/assets/146455588/44302eb5-3ee8-4dc9-a7e6-c67b6372b3f4)

We can, you can also see his inicials on the top-left side of the system, it says Witaj TT (test, test), which translates to Hello TT!

We can now change one of the members values, for example let's change Franek's name to **TestChange**

![image](https://github.com/etcshado/Projects/assets/146455588/a444c290-3169-4bcc-a2bb-1309c98595b4)

![image](https://github.com/etcshado/Projects/assets/146455588/e59b760e-ac6e-485e-8ed3-b65c2902dd5c)

![image](https://github.com/etcshado/Projects/assets/146455588/ee2fb8f5-14fd-42be-b9ad-881b87f35f90)

It works! Now let me show you one more thing, class Pracownik is an abstract class.

Class Uzytkownic inherits after Pracownik,

Class Brygadzista inherits after Uzytkownik.

So it looks like this Pracownik <-- Uzytkownik <-- Brygadzista

So if we delete an instance of Brygadzista, it should delete the Pracownik as well, right? Lets try that!

I'll delete every brygadzista, whose name starts with the letter: "**O**".

![image](https://github.com/etcshado/Projects/assets/146455588/b1429933-ba67-489f-b052-037551ef168a)

![image](https://github.com/etcshado/Projects/assets/146455588/e5db7a7e-c63e-440e-a7ea-3b983f5ddcee)

Now let's see if they are still seen in the Pracownik view.

![image](https://github.com/etcshado/Projects/assets/146455588/5e8113d7-f722-4f9b-8c37-e6adf86752a3)

And as we can see, there are no workers, whose name start with the letter "O".

Last thing that i want to show, is the ability to see what workers are in a certain department "Dzial".

Remember the Brygadzista that we created? His name was Test Test, let's see if we can see him in Dzial2 list of workers.

Thankfully, all we have to do is to click on the ListaPracownikow button:

![image](https://github.com/etcshado/Projects/assets/146455588/fd778968-31f5-4154-ab94-8159cd434ed2)

![image](https://github.com/etcshado/Projects/assets/146455588/e67c37fb-3cb6-4d90-abfc-7ae172fce6f8)

Yes we can!, there are few thing's left, but they are not as essential to show, as the rest. Thank you for visiting!












