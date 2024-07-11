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
![image](https://github.com/etcshado/Projects/assets/146455588/fc09d881-6270-44f7-8889-df72ce8d5403)

Here are all the workers of the company:

![image](https://github.com/etcshado/Projects/assets/146455588/f593725b-7586-4440-8a16-2db168d30c85)

You can create a new object:

![image](https://github.com/etcshado/Projects/assets/146455588/a41e6ef6-5c47-49b4-9220-8457e19dea00)

![image](https://github.com/etcshado/Projects/assets/146455588/56f042f8-2126-4ade-87f7-a6c942789da6)

So with that, i just created a new Brygadzista, with the name of **Test**, surname of **Test**, DOB of current date, Dzial of **Dzial2**, Login of **Test** and a Password of **123**.

Let's see if the system can see him.

![image](https://github.com/etcshado/Projects/assets/146455588/53224b5b-cd0a-461b-ba44-601d765784c1)

It sure can, how about logging as him?

![image](https://github.com/etcshado/Projects/assets/146455588/a076c859-ca33-456d-b86b-22d136e2abee)

We can, you can also see his inicials on the top-left side of the system, it says Witaj TT (test, test), which translates to Hello TT!

![image](https://github.com/etcshado/Projects/assets/146455588/b0689988-c74e-4bab-8985-2a705b25d36b)

We can now change on of the members values, for example let's change Franek's name to **TestChange**

![image](https://github.com/etcshado/Projects/assets/146455588/af233c29-843a-4744-8b22-ef72667d158a)

![image](https://github.com/etcshado/Projects/assets/146455588/df3c3575-96ba-47c9-acbe-54e30e456e9c)

Well, it works. Now let me show you one more thing, class Pracownik is an abstract class.

Class Uzytkownic inherits after Pracownik,

Class Brygadzista inherits after Uzytkownik.

So it looks like this Pracownik <-- Uzytkownik <-- Brygadzista

So if we delete an instance of Brygadzista, it sure delete the Pracownik as well, right? Lets try that!

I'll delete every brygadzista, whose name starts with the letter: "**O**".

![image](https://github.com/etcshado/Projects/assets/146455588/0f52ba51-5b18-44c0-a71f-4d599f456df9)

![image](https://github.com/etcshado/Projects/assets/146455588/c0f3743a-0298-413b-bcd2-f184d627457b)

Now let's see if they are still seen in the Pracownik view.

![image](https://github.com/etcshado/Projects/assets/146455588/4e70985c-63bd-4d7c-89e4-8d08c810292e)

And as we can see, there are not workers, whose name start with the letter "O".

Last thing that i want to show, is the ability to see what workers are in a certain department "Dzial".

Thankfully, all we have to do is to click on the ListaPracownikow button:

![image](https://github.com/etcshado/Projects/assets/146455588/b4f1ca81-3d56-434c-bbc3-23a247126008)

![image](https://github.com/etcshado/Projects/assets/146455588/da6636c1-4d77-4235-9af2-7908db122464)

There are few thing's left, but they are not as essential to show, as the rest. Thank you for visiting!












