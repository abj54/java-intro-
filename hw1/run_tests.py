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
        command = """java CoursePrep"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertFalse("..." in got)

class Problem2(unittest.TestCase):
    
    def test1(self):
        command = "java WindChill 32 15"
        sought = """21.588988890532022"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem3(unittest.TestCase):
    
    def test1(self):
        command = "java DayOfWeek 3 14 1879"
        sought = """5"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)


class Problem4(unittest.TestCase):
    
    def test1(self):
        command = "java GreatCircle 48.87 -2.33 37.8 -122.4"
        sought = """8701.389543238289"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem5(unittest.TestCase):
    
    def test1(self):
        command = "java ThreeSort 1 2 3"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test2(self):
        command = "java ThreeSort 1 3 2"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test3(self):
        command = "java ThreeSort 2 1 3"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test4(self):
        command = "java ThreeSort 2 3 1"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test5(self):
        command = "java ThreeSort 3 1 2"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test6(self):
        command = "java ThreeSort 3 2 1"
        sought = """1 2 3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)
        
class Problem6(unittest.TestCase):

    def test1(self):
        command = "java ThreeDice"
        sought = """Random integer from [3, 18]"""
        got = int(run(command))
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertTrue(got >= 3 and got <= 18)

class Problem7(unittest.TestCase):

    def test1(self):
        command = """java PrimeCounter 100"""
        sought = """25"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

    def test2(self):
        command = """java PrimeCounter 1000000"""
        sought = """78498"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)
    
class Problem8(unittest.TestCase):

    def test1(self):
        command = "java Ramanujan 40000"
        sought = """1729 = 1^3 + 12^3 = 9^3 + 10^3
4104 = 2^3 + 16^3 = 9^3 + 15^3
13832 = 2^3 + 24^3 = 18^3 + 20^3
39312 = 2^3 + 34^3 = 15^3 + 33^3
32832 = 4^3 + 32^3 = 18^3 + 30^3
20683 = 10^3 + 27^3 = 19^3 + 24^3"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(sorted(got), sorted(sought))

class Problem9(unittest.TestCase):

    def test1(self):
        command = """java Distance"""
        sought = """13.0"""
        got = run(command, "5 -9 1 10 -1 1 5 -5 9 6 7 4")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem10(unittest.TestCase):

    def test1(self):
        command = """java Transpose"""
        sought = """3 3
  1.00000   4.00000   7.00000 
  2.00000   5.00000   8.00000 
  3.00000   6.00000   9.00000"""
        got = run(command, "3 3 1 2 3 4 5 6 7 8 9")
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

class Problem11(unittest.TestCase):

    def test1(self):
        command = "java Power 3 5"
        sought = """243"""
        got = run(command)
        self.assertNotEquals(got, "__TIMEOUT__")
        self.assertEquals(got, sought)

if __name__ == '__main__':
    unittest.main()
