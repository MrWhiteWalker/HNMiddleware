**Steps to run this project**
> 1. Build a docker image: 
> **_`docker image build -t hn_middleware .`_**
> 2. Run a container using the created image:  **_`docker container run -p 8080:8080 hn_middleware`_**
> 3. Open **_`http://localhost:8080/swagger-ui.html`_** in the browser to see the list of API endpoints.

**NOTE**:

1. If you need to run the project on a different port, you can specify it in the **_`docker container run`_** command. You'd need to use the same port in the SwaggerConfig.java if you want to use swagger too.
2. Project uses system memory as a caching mechanism. In a real application, we'd want the caches to stay even if we restart the system so we can opt for a database solution.
3. Due to the structure in which hackernews api gives data, fetching top stories and top comments takes a lot of time. We can optimise top stories api call using a database to sync the data.  
 