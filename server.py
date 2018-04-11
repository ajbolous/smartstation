from flask import Flask
import json

app = Flask(__name__)
#HI
# hello
@app.route("/sayHello")
def hello(req):
    print(req)
    return "Hello World!"

@app.route("/getStations")
def addNumbers():

    json.loads()
    stations = [
        {
            "id": 1,
            "name": "sfafri",
            "location": {
                "lat": 12323,
                "lng": 123213
            }
        },
        {
            "id": 2,
            "name": "sfafri",
            "location": {
                "lat": 12323,
                "lng": 123213
            }
        }
    ]
    
    j = json.dumps(stations, indent=4)
    return j


app.run()
