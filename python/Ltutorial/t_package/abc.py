# this is abc.py

def print_self():
    """
    print abc.py information
    """
    print('This is t_package.abc.py')

def _private_1(name):
    return 'Hello, %s' % name

def _private_2(name):
    return 'Hi, %s' % name

def greeting(name):
    if len(name) > 3:
        return _private_1(name)
    else:
        return _private_2(name)