## AWS Revision

## Description

I created this programme when I was simultaneously revising for my AWS Cloud Practitioner exam and refreshing my Java skills!<br>
My usual method of revising is to write questions for myself based on my notes. I then keep reading through the questions until I can answer them right every time. This seemed like a good opportunity to automate the process a little, so I wrote all my questions in Java instead of my usual Word document and then wrote a programme to randomise the order and ask me questions one by one. If I don't get the answer right, I can add it to a list to revise later, and the programme will keep going over the revision list until I get everything right. <br>
It's a simple implementation but was a good way to revise and practise Java at the same time. And I passed my exam so it must have worked! <br>
Please note if you want to use it to revise that these are all my own questions based on my own notes. Therefore there may be typos or mistakes.

## How to use

### Setup (mac)

1. This project requires Java Development Kit (JDK) 16 or later. Verify you have the right version of Java:

```bash
java --version
javac --version
```

2. Clone this repo and move into the directory:

```bash
git clone https://github.com/evg4/AWSRevision.git AWSRevision
cd AWSRevision
```

3. Decide which question categories you want to revise. In the questionBank in the main class, you can keep all 19 categories, or delete/comment out any lines you don't want to include. You need at least one category. Save your changes.

4. Compile and run the programme

```bash
javac AWSRevisionProgramme/*.java
java AWSRevisionProgramme.AWSRevision
```

### Using the programme

Everything is run from the terminal and should be self-explanatory. If something needs further explanation, please contact me!

## Licence

Please see license document.
