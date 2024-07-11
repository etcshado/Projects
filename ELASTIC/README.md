This is my journey of learning a SIEM solution, Elastic.

1. Register on their website.

2. Create a Deployment

3. Enroll an agent (I used a Kali Linux machine)

4. Confirm the Elastic service is running on the enrolled machine

![t1](https://github.com/etcshado/Projects/assets/146455588/c9e78566-5404-42e8-a25c-626b175a4640)

5. You can then check for Observability -> Logs to check if any data is flowing in.

6. Let's create an alert that will search for any nmap scans executing on the agent. It will notify me via an Email if an alert is catched.

![t2](https://github.com/etcshado/Projects/assets/146455588/952180f9-f59c-4abc-806c-57281df64fc5)

All I searched for was "nmap" string in process.args or event.action

7. Execute nmap_test1.sh, and wait for the alert to catch that, and after a few minutes (depending of what scheduling you set up) I received an email

![t3](https://github.com/etcshado/Projects/assets/146455588/e7fb34ca-694e-40c9-b618-a6915e5b3f07)

8. Nice, it works, now let's try to make an alert that will catch common find command to search for SUID permissions.

![t4](https://github.com/etcshado/Projects/assets/146455588/3d17c036-f6b6-4a34-8fe5-365f2ab1f90c)

9. Now execute SUID_test1.sh and wait for the alert to fire off. After a few minutes, let's check the Security -> Alerts
   
![t5](https://github.com/etcshado/Projects/assets/146455588/4f82d1c6-1d28-4a4c-9bc4-f3db2f950fa9)

10. And it did catch it! Nice! Moving on, let's explore a bit. I found this alert called Potential Reverse Shell. Let's enable it and test it out!

![t6](https://github.com/etcshado/Projects/assets/146455588/f6eb1638-2769-4fd7-95a0-15a41e8895c4)

11. Now let's setup a simple revshell on the agent.

![t7](https://github.com/etcshado/Projects/assets/146455588/455dbe1d-38bc-4f16-b035-aecb889ece51)

12. Wait for few minutes and...

![t8](https://github.com/etcshado/Projects/assets/146455588/2d5685eb-6831-481b-abb2-154272e65070)

13. The custom alert we enabled and tested did in fact catch the reverse shell. Nice!


