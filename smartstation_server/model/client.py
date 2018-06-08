from .base import SerializableBase

class Client(SerializableBase):
    def __init__(self):
        self.id: str = ''
        self.firstName: str = ''
        self.lastName: str = ''
        self.email: str = ''
        self.password: str = ''
        self.CombinedCard: str = ''
        self.CreditCard: str = ''
        self.ExpDate: str = ''
        self.CVV: str = ''
        
        
