# premium-SOAP

## Deskripsi singkat web service
Service SOAP pada tugas ini digunakan untuk mengelola permintaan subscription dari base-app untuk poscaster. Service ini juga digunakan untuk logging pada request-request tersebut.

## Skema basis data yang digunakan
![image](https://github.com/IF3110-2023-02-37/premium-SOAP/assets/88760287/a15ec1c8-16a1-498e-bdc9-34f920f5fccd)

**Subscription**
- creatorID : int
- userName : string
- username : string
- status : 'PENDING' | 'ACCEPTED' | 'REJECTED'

**Logging**
- id : int
- IP : string
- endpoint : string
- requested_at : datetime

## Endpoint API
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:end="http://endpoint/"> <br>
   <soapenv:Header> <br>
   	<soapenv:Authorization>xuyewPk6NDlBewftLtHJVf=PAb3</soapenv:Authorization> <br>
   </soapenv:Header> <br>
   <soapenv:Body> <br>
   	<end:getSubs> <br>
   	<podcaster>jokowi</podcaster> <br>
      </end:getSubs> <br>
   </soapenv:Body> <br>
</soapenv:Envelope> <br>

## Pembagian Tugas
- database seeder : 13521050
- fungsi createSubs : 13521050
- fungsi acceptReview : 13521050
- fungsi getReview : 13521050
- fungsi create pending subs : 13521050
- deletion endpoint : 13521050
- api key : 13521120
- Log : 13521168
- Log Handler : 13521168
- docker : 13521120
- fungsi getSubsWithSubcriber : 13521050
