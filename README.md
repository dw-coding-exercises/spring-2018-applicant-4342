# Democracy Works Coding Challenge~~~~

## Installation
1. Unzip the folder.
2. Get on the command line and change your directory to where you unzipped the folder.
3. Enter the directory of the folder with `cd my-exercise`.
4. Run ```lein ring server``` to get the server running and the libraries installed.

## Technologies Used
I only added two libraries: `clj-http` and `ring/ring-mock`. 

`clj-http` was used to send the request to the Democracy Works API. You can find documentation on this Clojure library [here](https://github.com/dakrone/clj-http).

`ring/ring-mock` was used to test and mock requests to this app's endpoints. You can find documentation on this Clojure library [here](https://github.com/ring-clojure/ring-mock).

## Meeting the Requirements
In this section I will discuss how I met all the basic requirements of this challenge:

    Create the missing /search route
    Ingest the incoming form parameters
    Derive a basic set of OCD-IDs from the address
    Retrieve upcoming elections from the Democracy Works election API using those OCD-IDs
    Display any matching elections to the user

### Create the missing /search route
I added a `/search` POST endpoint in `core.clj` to the routes within the app.

### Ingest the incoming form parameters
The previously discussed endpoint takes the form parameters and passed it on to the `page()` in `search.clj`. As a bonus, I tried to validate the forms as much as possible in my limited knowledge of Clojure and the time period.

### Derive a basic set of OCD-IDs from the address
Using the form parameters, I was able to form two OCD-IDs: a state OCD-ID and place OCD-ID. This is specifically done in the `create-turbovote-request()` of `search.clj`.

### Retrieve upcoming elections from the Democracy Works election API using those OCD-IDs
I was able to poll the TurboVote API for the upcoming elections as seen in `send-turbovote-request()`.

### Display any matching elections to the user
Although not in an ideal format, I'm able to display the matching elections to the user through the `display-matching-elections()` function.

## Bonuses
I started to add tests but I got stuck trying to make a test for the missing `/search` endpoint.