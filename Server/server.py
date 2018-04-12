from flask import Flask
import json
from database_handler import DatabaseHandler

app = Flask(__name__)
app.debug = True

dbHandler = DatabaseHandler()

@app.route("/getStations")
def getStations():
    jsonObjects = []
    for station in dbHandler.stations:
        jsonObjects.append(station.toJson())
    return json.dumps(jsonObjects)


app.run()
