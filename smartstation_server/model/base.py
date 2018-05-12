class SerializableBase():
    def __init__(self):
        pass

    def toJson(self):
        return self.__dict__

    @classmethod
    def fromJson(cls, jsonObj):
        obj = cls()
        #jsonObj.__delitem__("_id")
        obj.__dict__.update(jsonObj)
        return obj