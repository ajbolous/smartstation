from flask import Flask, jsonify, request

app = Flask(__name__)
app.debug = True  

from .views.routes import *
from .views.users import *
from .views.stations import *