# demoparser

Requirements:
Fetch data from https://s3.amazonaws.com/premise-android-interview/task-list.json

Data Schema :
categories are: 
{ 
  "category_name": "Category XYZ",
  "icon": "https://s3.amazonaws.com/url/to/category/icon.png",
  "tasks": [ ... list of tasks ...]
}

tasks are : 
{
  "task_name": "Task XYZ",
  "icon": "https://s3.amazonaws.com/url/to/task/icon.png",
  "enabled": true,
  "earning": 1.23
}

Categories can be shown in a list like:

[(icon in 20dpx20dp)    category__name |
- 5 dp padding around everything
- 5 dp gap between icon and category name
- category name must be vertically centered, left aligned

tapping the category would open the tasks page for that category

| (icon of task in 20dpx20dp)  task_name earning |

- 5 dp padding around everything
- 5 dp gap between icon and task
- 5 dp gap between task name and earning
- task to be left aligned
- earning right aligned
- task name to be wrapped to new line if it doesn't fit
- task should be greyed + not clickable if not enabled


-------------------------------------------------------
This application should manage all of the requirements listed above, in addition I did the following things..
* Used a RecyclerView+ViewHolder vs. ListView
* Used fragments vs. multiple activities
* UI States are stored correctly when rotating screen, etc. 
* Once loaded, network requests are not made again. (can go offline after loading the list)
* Added a couple of unit tests
* Used theme/style to apply the color changes to the text views.
* Should not have any heavy tasks on UI thread

Things I would have liked to have done with some more time
* Address no network, and showing a message to the user, currently it does not crash when there is no network, 
but does not display a message either. 
* More Unit Tests & Espresso Tests (Espresso recorder is not available yet in current AS 2.2 preview)


