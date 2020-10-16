Part 1. Java and Selenium WebDriver       
Develop automation tests using Java and Selenium WebDriver. Please use any patterns, selectors, approaches you like. 

The main page to start with: https://www.wiley.com/en-us
1.	Test needs to open the page and check items under Who We Serve sub-head
•	there should be 11 items under resources sub-header
•	titles are “Students”, “Instructors”, “Book Authors”, “Professionals”, “Researchers”, “Institutions”, “Librarians”, “Corporations”, “Societies”, “Journal Editors”, “Government”

2.	Search functionality. Test should enter “Java” in the search input box and do not press the search button (with magnifying glass icon) 
•	check area with related content is displayed right under the search header  
 
3.	Enter “Java” in the search input, press the search button and apply checks to verify that
•	only titles containing “Java” are displayed  
•	there are 10 titles on the page 
•	each title has at least one “Add to Cart” button for E-Book/Print version and “VIEW ON WILEY ONLINE LIBRARY” for O-BOOK version 
 
4.	Go to Subjects top menu, select Education
•	Check “Education” header is displayed  
•	13 items are displayed under “Subjects” on the left side of the screen and the texts are: "Information & Library Science",    "Education & Public Policy",  "K-12 General",  "Higher Education General",   "Vocational Technology",   "Conflict Resolution & Mediation (School settings)",   "Curriculum Tools- General",   "Special Educational Needs",   "Theory of Education",  "Education Special Topics",  "Educational Research & Statistics",  "Literacy & Reading",  "Classroom Management"

Part 2. API   
Develop automation tests to check status and response using Java and any library of your choice.  
 
1.	Check that GET call to 
https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java 
returns response with at least these parts:
•	4 suggestions contain attribute “term” : value starting with the preformatted highlighted word java inside like <span class=\"search-highlight\">java</span>
•	4 pages with attribute “title”: value includes word Wiley 

2.	There is a simple HTTP Request & Response Service https://httpbin.org. Suggest tests that verify the below end point works as intended:
https://httpbin.org/#/Dynamic_data/post_delay__delay_ 
POST/delay/{delay} returns a delayed response (max of 10 seconds).

3.	Suggest tests that verify the below end point works as intended:
https://httpbin.org/#/Images/get_image_png
GET/image/png returns a simple PNG image
