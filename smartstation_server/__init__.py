from flask.json import JSONEncoder
from flask import Flask
from .model.base import SerializableBase

class SerializableBaseJSONEncoder(JSONEncoder):
    def default(self, obj):
        if issubclass(obj.__class__, SerializableBase):
            return obj.toJson()
        return JSONEncoder.default(self, obj)

app = Flask(__name__)
app.debug = True  
app.json_encoder = SerializableBaseJSONEncoder

from .views.routes import *
from .views.users import *
from .views.stations import *
from .views.buses import *