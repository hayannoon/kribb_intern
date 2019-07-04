import xml.etree.ElementTree as elemTree
import openpyxl
import numpy as np

xmlFilePath, serverFilePath = [], []
only_Server, only_Xml = [], []  # 엑셀에 넣을 배열들
duplication = dict()
flag = 0
#파일마다 바꿔주어야할것 : 각각의 파일 Path(xmlPath, serverPath변수,
xmlPath, serverPath = 0,0
xmlRealPath, serverReaPath = [], []
try:
    xmlPath = "D:/minwoo/Meta/MAGIC_20181213_modified2.xml"
    targetXML = open(xmlPath, 'r', encoding='UTF8')
#    targetXML = open('MOTIE_20190103.xml','r',encoding='UTF8')
    # tree = elemTree.parse("D:/minwoo/Meta/MOTIE_20190103.xml") #파일 불러오고
    tree = elemTree.parse(targetXML)
    root = tree.getroot()
    # root 가져온다.

    for ele in root.findall("./project_set/first_data/register_file/file"):
        #(trash, item) = ele.get('path').split('igda/')
        xmlRealPath.append(ele.get('path')) #original 경로 저장
        (trash,item) = ele.get('path').rsplit('/',1)
        xmlFilePath.append(item)


except IOError as err:
    print("Xml File Error : " + str(err))
    # 여기까지 왔으면 xmlFilePath에 xml 파일리스트 저장완료


try:  # Server 파일목록 텍스트파일 읽어와서 파일명 파싱해서 저장
    serverPath = "D:/minwoo/Meta/magic.txt"
    targetServer = open(serverPath)
    lines = targetServer.readlines()

    for line in lines:
        serverReaPath.append(line) #서버 original 경로 저장
        (trash, value) = line.rsplit('/',1)  # 이부분은 파일마다 수정해줘야한다.
        serverFilePath.append(value.rstrip('\n'))  # 개행문자 제거하고 추가

except IOError as err:
    print("Server File Error: " + str(err))
    # 여기까지 왔으면 serverFilePath에 서버 파일리스트 저장완료


# 우선 서버정보를 모르니, 서버의 파일경로가 serverFilePath에 저장되어있다고 가정
xmlFilePath.sort()
serverFilePath.sort()
# 정렬
(trash, temp) = serverPath.split('Meta/')
(output_fileName, temp) = temp.split('.txt')
print(output_fileName)  # outputFile 이름 저장


if np.array_equal(xmlFilePath, serverFilePath):
    flag = 1
    # 두 xml과 서버 값이 동일한경우 flag값 변경(이름지을때 사용)

#only_Server = [x for x in serverFilePath if x not in xmlFilePath]
# 서버에있고, xml에 없는 목록 저장

for x in serverReaPath:
    (trash,temp) = x.rsplit('/',1)
    if temp.rstrip('\n') not in xmlFilePath:
        only_Server.append(x)
    #개선한버전

#only_Xml = [x for x in xmlFilePath if x not in serverFilePath]
# xml에 있고, 서버에 없는 목록 저장

for x in xmlRealPath:
    (trash,temp) = x.rsplit('/',1)
    if temp not in serverFilePath:
        only_Xml.append(x)
    #개선한버전

for list in xmlFilePath:
    if list not in duplication.keys():
        duplication[list] = 1
    else:
        duplication[list] += 1
#duplication dictionary에 각 리스트별로 몇개가 중복되는지 입력한다.








# 결과값 엑셀파일에 쓰기
wb = openpyxl.Workbook()
sheet = wb.active

sheet['A1'] = '서버 데이터'
sheet['B1'] = 'XML 데이터'
sheet['C1'] = '서버에만 있는 데이터'
sheet['D1'] = 'XML에만 있는 데이터'
sheet['E1'] = '중복 제거한 XML데이터'
sheet['F1'] = '각 XML 데이터 개수'

index = 2
for item in serverFilePath:
    sheet['A' + str(index)] = item
    index += 1
    # 첫번째 칼럼에 serverFilepath 입력

index = 2
for item in xmlFilePath:
    sheet['B' + str(index)] = item
    index += 1
    # 두번째 칼럼에 xmlFilepath 입력

index = 2
for item in only_Server:
    sheet['C' + str(index)] = item
    index += 1
    # 세번째 칼럼에 only_Server 입력

index = 2
for item in only_Xml:
    sheet['D' + str(index)] = item
    index += 1
    # 네번째 칼럼에 only_xml 입력

index = 2
for item in duplication.keys():
    sheet['E' + str(index)] = item
    index += 1
    # 다섯번째 칼럼에 중복 제거된 xml 리스트 입력

index = 2
for item in duplication.values():
    sheet['F' + str(index)] = item
    index += 1
    #여섯번째 칼럼에 중복 제거된 xml 리스트 각각의 개수 입력

if(flag == 1):
    wb.save(output_fileName + '_Matched.xlsx')
else:
    wb.save(output_fileName + '_Not_Matched.xlsx')
