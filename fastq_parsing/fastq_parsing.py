import os
import sys


def search(dirname):
    filenames = os.listdir(dirname)  # 하위 항목들(파일 혹은 디렉토리) 저장
    for filename in filenames:  # 하위 항목들로 반복문
        # 파일이름에 경로 추가해서 절대경로 만들어주고
        full_filename = os.path.join(dirname, filename)
        if os.path.isdir(full_filename):  # 해당 항목이 디렉터리인경우 재귀호출
            search(full_filename)
        elif os.path.isfile(full_filename):  # 해당 항목이 파일인 경우
            ext = os.path.splitext(full_filename)[-1]  # 확장자명 얻어와서 테스트해보고
            if ext == '.txt':
                validation(full_filename)  # 확장자명 일치하면 validation 호출


def validation(filepath):  # 유효성 확인 함수
    try:
        flag = 0
        target = open(filepath, 'r')  # 읽기 전용으로 파일 연다.
        lines = target.readlines()
        # lines에 text값들 복사된다.

        numberOfRead = 0  # Read 개수 초기화
        numberOfBase = 0  # Base 개수 초기화
        i = 1
        length = 0

        for line in lines:  # 파일을 한줄한줄 순서대로 읽으며 반복문을 돈다.
            if i % 4 == 2:  # 한 Read 내에서 두번째줄인경우
                if '\n' in line:  # 개행이 섞여있다면 전체길이에서 1 뺀다.
                    length = len(line) - 1
                else:  # 개행 없으면 그대로 길이 저장
                    length = len(line)
                numberOfRead += 1  # 4줄중에서 한번진입하므로, Read수 1 증가
                numberOfBase += length  # Base수는 sequence 길이만큼 증가

                if (length % 4) != 0:  # 그리고 sequence가 4의 배수가 아닌경우
                    flag = 1  # flag값 바꾸고
                    print(str(filepath) + " #" + str((int(i / 4) + 1)) +
                          " Read is NOT valid!!! (the number of read is " + str(length) + ")")
                    # 유효하지 않다는 메시지 출력
                # else:
                #    print("#" + str(( int(i/4)+1) ) + " Read is valid")
                i += 1
            else:
                i += 1

        if flag == 0:  # flag값에 따라 유효성 여부 출력
            print("This file is VALID")
        else:
            print("This file is NOT VALID")

        print(str(filepath) + " numberOfRead : " + str(numberOfRead))
        print(str(filepath) + " numberOfBase : " + str(numberOfBase))
        print()  # 결과값 출력해주고 개행
        target.close()  # 파일 닫는다.

    except IOError as err:
        print("Input file Error: " + str(err))


print()  # 개행
p = str(sys.argv[1])  # 첫번째 인자값에서 경로 받아와서
search(p)  # 함수 호출
# search("D:/minwoo/fastq_parsing/sampledirectory")
