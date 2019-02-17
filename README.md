# ImageUploadService
## Supported operations
Base Path: http://localhost:8080/ImageUploadService/v0/ims/image
1. Upload Image
```
HTTP method : POST
URL : http://localhost:8080/ImageUploadService/v0/ims/image?user=guru&overwrite=true
#Query params : 
user -- mandatory -- specify user id
overwrite -- optional -- boolean flag to indicate if file can be overwritten. Default file overwrite fails
file -- manadatory -- upload File
```
Response contains file name, URL to download file, date, imageData field will be populated during download
```
Response
{
    "imageResource": [
        {
            "fileName": "suzuki",
            "imageUrl": "http://localhost:8080/ImageUploadService/v0/ims/image/suzuki?type=jpg&user=guru",
            "lastModifiedDate": "02/17/2019 12:18:17 PST",
            "imageData": null,
            "deleted": false
        }
    ],
    "totalCount": 1,
    "errorMessage": null
}
```
2. Search Images
User will be able to search images by keyword or when no keyword is specified, search will list all files.
Search is paginated. Specify page number and fetch to navigate to different resultsets
```
HTTP Method : GET
URL : http://localhost:8080/ImageUploadService/v0/ims/image?user=guru&pgn=1&cnt=10&keyword=suzuki
#Query params
user -- mandatory -- user id
pgn -- optional -- page number
cnt -- optional -- fetch count or results per page
keyword -- optional -- file name as search word
```
Response contains list of files with data file name, URL to download file, date
```
{
    "imageResource": [
        {
            "fileName": "suzuki",
            "imageUrl": "http://localhost:8080/ImageUploadService/v0/ims/image/suzuki?type=jpg&user=guru",
            "lastModifiedDate": "02/17/2019 12:18:17 PST",
            "imageData": null,
            "deleted": false
        }
    ],
    "totalCount": 1,
    "errorMessage": null
}
```
3. Download Image
Upon clicking the image link in the above results, service returns response. The response contains image data as byte array. It can converted to file for given file type.
```
HTTP method : GET
URL : http://localhost:8080/ImageUploadService/v0/ims/image/suzuki?type=jpg&user=guru
#Path Param
file name as path param , in the above URL 'suzuki' is path param
#Query Params 
user -- mandatory -- user id
type -- mandatory -- file type extension, this is needed since two files with same name but different types can be saved
```
Response contains file details and data in the form of byte arrray. Byte array can be transformed to file by client for a given type
```
{
    "imageResource": [
        {
            "fileName": "oyTppU6M_400x400",
            "imageUrl": "http://localhost:8080/ImageUploadService/v0/ims/image/oyTppU6M_400x400?type=jpg&user=guru",
            "lastModifiedDate": "02/17/2019 11:51:46 PST",
            "imageData": "/9j/4AAQSkZJRgABAQAAAQABAAD/4gKgSUNDX1BST0ZJTEUAAQEAAAKQbGNtcwQwAABtbnRyUkdCIFhZWiAH4QADABcAEQACADBhY3NwQVBQTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAAAADTLWxjbXMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAtkZXNjAAABCAAAADhjcHJ0AAABQAAAAE53dHB0AAABkAAAABRjaGFkAAABpAAAACxyWFlaAAAB0AAAABRiWFlaAAAB5AAAABRnWFlaAAAB+AAAABRyVFJDAAACDAAAACBnVFJDAAACLAAAACBiVFJDAAACTAAAACBjaHJtAAACbAAAACRtbHV/lP//Z",
            "deleted": false
        }
    ],
    "totalCount": 1,
    "errorMessage": null
}
```
4. Delete Image
Image can be deleted by invoking HTTP delete method on the image URL
```
HTTP method : DELETE
URL : http://localhost:8080/ImageUploadService/v0/ims/image/suzuki?type=jpg&user=guru
#Path Param
file name as path param , in the above URL 'suzuki' is path param
#Query Params 
user -- mandatory -- user id
type -- mandatory -- file type extension, this is needed to distinguish two files with same name but different types
```
Response contains deleted file details and boolean field deleted indicates file is deleted.
```
{
    "imageResource": [
        {
            "fileName": "suzuki",
            "imageUrl": "http://localhost:8080/ImageUploadService/v0/ims/image/suzuki?type=jpg&user=guru",
            "lastModifiedDate": "02/17/2019 12:18:17 PST",
            "imageData": null,
            "deleted": true
        }
    ],
    "totalCount": 1,
    "errorMessage": null
}
