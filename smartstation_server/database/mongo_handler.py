import pymongo

uri = 'mongodb://smartstations:JcUaeNO67QKdWKyOESxF5j7d3LX7f6IfIhHqwixS1XIFY6oJhAL5d15k6vPvV6W4DRtG2elLydzlTpJAiawePw==@smartstations.documents.azure.com:10255/?ssl=true&replicaSet=globaldb'
client = pymongo.MongoClient(uri)
collection = client.main.stations.find({'id':1})

for col in collection:
    print(col)