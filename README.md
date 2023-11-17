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

## Daftar Requirement
Berikut merupakan daftar requirements untuk aplikasi:
1. Java
2. JAX-WS
3. MySQL
4. maven

## Cara Instalasi
1. Lakukan instalasi requirements tersedia
2. Lakukan clone pada repository ini
3. Jalankan aplikasi docker
4. `docker-compose up --build` pada terminal

## Endpoint API
| Method        | Endpoint           | Fungsi  |
| --- |---| ---|
| GET | getSubs | mendapatkan informasi langganan (_subscriptions_) berdasarkan podcaster tertentu dan memanggil seluruh method yang ada pada SOAP service (getSubs, getSubsWithSubscriber, getPendingSubs, getAllSubs, acceptSubs, rejectSubs, createPendingSubs, deleteSubsPodcaster, deleteSubsSubscriber |

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

## Kelompok 37
- 13521050 Naufal Syifa Firdaus
- 13521120 Febryan Arota Hia
- 13521168 Satria Octavianus Nababan
