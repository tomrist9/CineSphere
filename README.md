# CineSphere
CinemaSphere is a movie information retrieval system that leverages the power of FeignClient and external APIs to provide comprehensive details about movies. 
With this application, users can effortlessly access rich movie information by utilizing the movieId parameter to interact with a custom-generated API.
## Key Features
**FeignClient Integration** : CinemaSphere seamlessly integrates FeignClient, a powerful and declarative web service client, to simplify communication with external APIs.
**Movie Information Retrieval**: By supplying a movieId, users can retrieve detailed information about a specific movie directly from the custom-generated API.
**Efficient and User-Friendly:** CinemaSphere is designed for efficiency and user-friendliness, ensuring a smooth experience for both developers utilizing the FeignClient functionality and end-users accessing movie information.

### Getting Started
To get started with CinemaSphere, follow these simple steps:
**1.Clone the Repository:** git clone https://github.com/tomrist9/cinesphere.git
**2.Navigate to the Project Directory:** cd cinesphere
**3.Install Dependencies:** npm install
**4.Run the Application:** npm start

#### How to Use
*To retrieve movie information, make a request to the custom-generated API using the movieId parameter. For example:* 
curl https://api.themoviedb.org/3/movies/{movieId}
*Replace {movieId} with the desired movie's ID.*

##### Contributing
*We welcome contributions to enhance CineSphere application.*

