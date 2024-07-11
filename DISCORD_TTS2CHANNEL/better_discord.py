import discord
import asyncio
from sys import argv
from jproperties import Properties

# usage is: python3 better_discord.py arguments to send to the text channel provided in config file (config.properties)
# example: python3 better_discord.py Hello everyone

configs = Properties()

with open('d1conf.properties', 'rb') as conf_file:
    configs.load(conf_file)
    conf_file.close()

TOKEN = configs.get("TOKEN").data
CHANNEL_ID = configs.get("CHANNEL_ID").data


def text():
    str = ""
    for i in argv[1:]:
        str += i
        str += " "
    return str


text_to_send = text()

intents = discord.Intents.default()
intents.message_content = True

client = discord.Client(intents=intents)

name = 'speech.txt'


@client.event
async def on_ready():
    print(f"The bot: {client.user.name} is working...")
    await send_chat()
    await on_disconnect()


@client.event
async def send_chat():
    channel = client.get_channel(int(CHANNEL_ID))
    await channel.send(text_to_send)


@client.event
async def on_disconnect():
    try:
        await client.http.close()
        await client.close()
        await print("kuniec")
    except Exception as e:
        pass


def start():
    client.run(TOKEN)


start()
