from flask import Flask

app = Flask(__name__)
app.debug = True  

from .views.routes import *
from .views.users import *
from .views.stations import *
