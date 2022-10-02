import threading
import requests

def main():

    for i in range(1000):
        print(f'Thread {i} starts...')
        thread = threading.Thread(target=make_request)
        thread.start()

    print('Done')

word = 'abcdefghijklmnopqrst'
size = 5

url = f'http://localhost:8080/Gradle___com_example___lab_01_1_0_SNAPSHOT_war/bonus?word={word}&size={size}'
def make_request():
    response = requests.get(url)
    print(response)

if __name__ == '__main__':
    main()