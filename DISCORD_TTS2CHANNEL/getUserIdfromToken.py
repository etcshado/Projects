import base64
import re
import traceback
from sys import argv


def getUserId(Token):
    tk = Token
    try:
        pat = r'(.*)\.(.*)\.(.*)'
        arr_match = re.match(pat, tk).groups()[0].encode('ascii')
        userId = base64.b64decode(arr_match + b'=' * (-len(arr_match) % 4))

    except Exception as e:
        print("An error has occured.")
        traceback.print_exc(e)
        return

    return userId


def start():
    global Token
    for i in argv:
        try:
            if len(re.match(r'(.*)\.(.*)\.(.*)', i).groups()) == 3:
                Token = i
                break
        except Exception as e:
            pass

    if Token is not None:
        print(getUserId(Token).decode('ascii'))
    else:
        print("Please enter a valid token \n Use this format: python3 getUserIdfromToken.py <discordToken> \n "
              "example: python3 getUserIdfromToken kidoaj2831.392g.gakopb8")


if __name__ == "__main__":
    start()
