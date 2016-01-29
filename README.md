# Asteriods
This will be the club rush demo for 2016

## How to Use:

### Fork the Repository

Click the "Fork" button at the top right of the webpage.

### Create the Project
Create a project using the libGDX project creater that can be found [here](https://libgdx.badlogicgames.com/download.html). Make sure the project has the name: "Asteroids" and the package name is "com.sshsgd.asteroids". Make sure the project has both and only "Controllers" and "Freetype" checked, then make sure that in advanced, "Eclipse" is checked. 

### Linking your files to the Repository
Copy the .git link on the right hand side of your fork of the repository. Open GitShell and cd to your project's folder. Type in the following commands:
```
git init
git remote add origin link-to-your-fork
git fetch origin master
git reset --hard FETCH_HEAD
git clean -df
```

From there, you can add the repository to GitHub for desktop and contribute to the repository
