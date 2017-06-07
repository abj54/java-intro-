import datetime, os, signal, subprocess, sys, time, unittest

def run(command, stdin = None, timeout = 30):
    """
    Runs the specified command using specified standard input (if any) and
    returns the output on success. If the command doesn't return within the
    specified time (in seconds), "__TIMEOUT__" is returned.
    """

    start = datetime.datetime.now()
    process = subprocess.Popen(command.split(),
                               stdin = subprocess.PIPE, 
                               stdout = subprocess.PIPE,
                               stderr = subprocess.STDOUT)
    if not stdin is None:
        process.stdin.write(stdin)
        process.stdin.close()
    while process.poll() is None:
        time.sleep(0.1)
        now = datetime.datetime.now()
        if (now - start).seconds > timeout:
            os.kill(process.pid, signal.SIGKILL)
            os.waitpid(-1, os.WNOHANG)
            return "__TIMEOUT__"
    return process.stdout.read().strip()

class Problem1(unittest.TestCase):
    
    def test1(self):
        command = "java ArrayST Pluto"
        sought = """Mercury 1
Venus 2
Earth 3
Mars 4
Jupiter 5
Saturn 6
Uranus 7
Neptune 8"""
        got = run(command, "Mercury Venus Earth Mars Jupiter Saturn Uranus Neptune Pluto")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem2(unittest.TestCase):
    
    def test1(self):
        command = "java FrequencyCounter 1"
        sought = """to be 2
distinct = 8
words = 10"""
        got = run(command, "to be or not to be that is the question")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem3(unittest.TestCase):
    
    def test1(self):
        command = "java AvgGPA"
        sought = "3.2835"
        got = run(command, "B A- A A- B A- A- B A+ B A+ A- B- B B+ B+ A C A+ F")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem4(unittest.TestCase):
    
    def test1(self):
        command = "java Spell data/misspellings.txt"
        sought = """wont:5370 -> won't
unconciousness:16122 -> unconsciousness
accidently:18948 -> accidentally
leaded:21907 -> led
wont:22062 -> won't
aquaintance:30601 -> acquaintance
wont:39087 -> won't
wont:50591 -> won't
planed:53591 -> planned
wont:53960 -> won't
Ukranian:58064 -> Ukrainian
wont:59650 -> won't
conciousness:59835 -> consciousness
occuring:59928 -> occurring"""
        got = run(command, open("data/war_and_peace.txt", "r").read())
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem5(unittest.TestCase):
    
    def test1(self):
        command = "java TreeTraversal"
        sought = """Pre-order:   F B A D C E G I H 
In-order:    A B C D E F G H I 
Post-order:  A C E D B H I G F 
Level-order: F B G A D I C E H"""
        got = run(command, "F B G A D I C E H")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

if __name__ == "__main__":
    unittest.main()
