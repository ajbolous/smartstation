from json import JSONEncoder
from flask import Flask
from .model.base import SerializableBase

app = Flask(__name__)
app.debug = True  

class SerializableBaseJSONEncoder(JSONEncoder):
    def default(self, obj):
        return obj.toJson() if issubclass(obj.__class__, SerializableBase) else JSONEncoder.default(self, obj)
        
app.json_encoder = SerializableBaseJSONEncoder

from .views.routes import *
from .views.users import *
from .views.stations import *
