
import time
import json
from ..model.station import Station
from typing import Dict, TypeVar, List, Callable
from ..model.base import SerializableBase

T = TypeVar('T')


class JsonCache():
    def __init__(self, filePath, T=SerializableBase, hashed=False,):
        self.filePath: str = filePath
        self.isDirty: bool = False
        self.timestamp = time.time()
        self.objects: List[T] = None
        self.hashedObjects: Dict[T] = {}
        self.hashed = hashed
        self.T = T

    def isValid(self):
        timeDiff = time.time() - self.timestamp
        return self.objects is not None and timeDiff < (60 * 1)

    def save(self):
        self.isDirty = True

    def all(self) -> List[T]:
        if not self.isValid():
            self.loadObjects()
        return self.objects

    def loadObjects(self):
        print('Loading cached json ' + self.filePath)
        self.objects = []
        self.timestamp = time.time()
        self.isDirty = False
        with open(self.filePath, 'r') as file:
            for obj in json.load(file):
                classObj = self.T.fromJson(obj)
                self.objects.append(classObj)
                if self.hashed:
                    self.hashedObjects[classObj.id] = classObj

    def writeObjects(self):
        self.isDirty = False
        self.timestamp = time.time()
        with open(self.filePath, 'w') as file:
            json.dump([obj.toJson() for obj in self.objects], file)

    def find(self, predicate: Callable[[T], bool]) -> List[T]:
        if not self.isValid():
            self.loadObjects()
        return [obj for obj in self.objects if predicate(obj)]

    def findById(self, id) -> List[T]:
        return self.find(lambda obj: obj.id == id)

    def get(self, field='id', value='') -> T:
        objs = self.find(lambda obj: obj.id == id)
        return objs[0] if len(objs) > 0 else None

    def getById(self, id) -> T:
        return self.get(field='id', value=id)
