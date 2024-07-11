from sys import exit
import threading
import speech_recognition as sr
import pyttsx3
from subprocess import run

r = sr.Recognizer()
engine = pyttsx3.init()  # Inicjalizacja pyttsx3 poza funkcją wątkowaną
lock = threading.Lock()  # Blokada do synchronizacji dostępu do mikrofonu

STOP = False
stopArr = ["stop", "break"]


def SpeakText(command):
    engine.say(command)
    engine.runAndWait()


def TurnOff():
    global STOP
    STOP = True
    print("Stopping the program...")
    exit(0)


def catch_and_send():
    global STOP
    while not STOP:
        try:
            with lock:  # Synchronizacja dostępu do mikrofonu
                with sr.Microphone() as src2:
                    r.adjust_for_ambient_noise(src2, duration=0.2)
                    audio2 = r.listen(src2)
            MyText = r.recognize_google(audio2, language='pl-PL')
            currText = str(MyText)

            if currText.lower() in stopArr:
                TurnOff()
            elif currText.lower() == "witam":
                run(["python3", "better_discord.py", "witam wszystkich"])
            else:
                print(currText)

        except sr.RequestError as e:
            print(f"Could not request results; {e}")
        except sr.UnknownValueError:
            print("Unknown audio input")
        except Exception as e:
            print(f"Exception: {e}")


catch_and_send()