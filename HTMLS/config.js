let theUrl = "http://localhost:8081/"

function logout()
{
    console.log("logout")
    sessionStorage.removeItem('role')
    sessionStorage.removeItem('id')
    sessionStorage.removeItem('username')
    sessionStorage.removeItem('password')
    window.location = "index.html"

}