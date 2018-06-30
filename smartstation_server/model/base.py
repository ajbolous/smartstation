class SerializableBase():
    def __init__(self):
        self.id = ''

    def toJson(self):
        return self.__dict__

    def __repr__(self):
        return "<Base " + str (self.id) + ">"
    @classmethod
    def fromJson(cls, jsonObj):
        obj = cls()
        #jsonObj.__delitem__('_id')
        obj.__dict__.update(jsonObj)
        return obj