#!/usr/bin/python3

from flask import Flask, jsonify, request
from subprocess import Popen, PIPE

app = Flask(__name__)


def c_binary_exists():
    cmd = "c-hello-world"
    try:
        p = Popen(cmd, stdout=PIPE)
        stdout = p.communicate()[0]
        return stdout == b"Hello, patched world!\n"
    except:
        return False


def rust_binary_exists():
    cmd = "rust-hello-world"
    try:
        p = Popen(cmd, stdout=PIPE)
        stdout = p.communicate()[0]
        return stdout == b"Hello, world!\n"
    except:
        return False


def kernel_module_exists():
    try:
        file = open("/dev/hello")
        content = file.read()
        file.close()
        return content == "Hello, World!\n\x00"
    except:
        return False

@app.route('/', methods = ['GET'])
def home():
    if request.method == 'GET':
        c = c_binary_exists()
        r = rust_binary_exists()
        m = kernel_module_exists()

        return jsonify({'c binary': c, 'rust binary': r, 'kernel module': m})


if __name__ == '__main__':
    app.run(port=8080)
