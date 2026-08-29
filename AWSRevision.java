package AWSRevisionProgramme;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

public class AWSRevision {

        private final Question[][] questionBank;
        private final Question[] questionBankStream;

        public AWSRevision(Question[][] questionBank) {
                this.questionBank = questionBank;
                this.questionBankStream = Stream
                                .of(this.questionBank)
                                .flatMap(Arrays::stream)
                                .toArray(Question[]::new);
        }

        private List<Integer> shuffleQuestions() {
                int numQuestions = questionBankStream.length;
                List<Integer> shuffled = new ArrayList<>();
                for (int n = 0; n < numQuestions; n++) {
                        shuffled.add(n);
                }
                Collections.shuffle(shuffled);
                return shuffled;
        }

        private void askQuestion(Scanner scanner, int question, List<Integer> revisionList) {
                System.out.println("QUESTION:");
                System.out.println(questionBankStream[question].question());
                System.out.println("Hit enter when you're ready to view the answer");
                scanner.nextLine();
                System.out.println("ANSWER:");
                System.out.println(questionBankStream[question].answer());
                System.out.println(
                                "Need to go over this one again? Type 'add' to review later then hit Enter, otherwise just hit Enter.");
                String add = scanner.nextLine();
                if (add.equalsIgnoreCase("add")) {
                        revisionList.add(question);
                        System.out.println("*** Question added to revision list ***");
                        System.out.println("");
                }

        }

        public void runQuiz() {
                List<Integer> questions = this.shuffleQuestions();
                List<Integer> questionNumbersToRevise = new ArrayList<Integer>();
                int totalQuestions = questions.size();
                int questionsToAsk = 0;
                Scanner scanner = new Scanner(System.in);
                System.out.println("========================================================");
                System.out.println("Welcome to your AWS revision session! Let's get started.");
                System.out.println("========================================================");
                System.out.println(String.format("You have %d questions in your question bank.", totalQuestions));

                // user decides how many questions they want to answer
                while (questionsToAsk < 1 || questionsToAsk > totalQuestions) {
                        System.out.println(String.format(
                                        "How many questions would you like? Type a number between 1 and %d and hit Enter.",
                                        totalQuestions));
                        try {
                                questionsToAsk = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException err) {
                                System.out.println("Please enter a number.");
                        }
                }

                // ask the specified number of questions from the shuffled questions
                for (int i = 0; i < questionsToAsk; i++) {
                        int question = questions.get(i);
                        int currentQuestionNum = i + 1;
                        System.out.println(
                                        String.format("== QUESTION %d OF %d ==", currentQuestionNum, questionsToAsk));
                        this.askQuestion(scanner, question, questionNumbersToRevise);
                }

                // repeat the questions that need revising until there are none left
                while (!questionNumbersToRevise.isEmpty()) {
                        List<Integer> questionNumbersToRevise2 = new ArrayList<Integer>();
                        System.out.println(String.format("You need to revise %d questions.",
                                        questionNumbersToRevise.size()));
                        System.out.println(
                                        "Hit Enter to revise these now.");
                        scanner.nextLine();

                        for (int i = 0; i < questionNumbersToRevise.size(); i++) {
                                int question = questionNumbersToRevise.get(i);
                                int currentQuestionNum = i + 1;
                                System.out.println(String.format("== QUESTION %d OF %d ==", currentQuestionNum,
                                                questionNumbersToRevise.size()));
                                this.askQuestion(scanner, question,
                                                questionNumbersToRevise2);
                        }
                        questionNumbersToRevise = questionNumbersToRevise2;
                }
                System.out.println("You got everything right! The quiz has finished.");

                scanner.close();

        }

        public static void main(String[] args) {
                final Question[][] questionBank = { AWSAllQuestions.CLOUD_COMPUTING_QUESTIONS,
                                AWSAllQuestions.IAM_QUESTIONS,
                                AWSAllQuestions.EC2_QUESTIONS,
                                AWSAllQuestions.ELB_ASG_QUESTIONS,
                                AWSAllQuestions.S3_QUESTIONS,
                                AWSAllQuestions.DATABASE_ANALYTICS_QUESTIONS,
                                AWSAllQuestions.OTHER_COMPUTE_QUESTIONS,
                                AWSAllQuestions.DEPLOYMENTS_INFRA_AT_SCALE_QUESTIONS,
                                AWSAllQuestions.GLOBAL_INFR_QUESTIONS,
                                AWSAllQuestions.CLOUD_INTEGRATIONS_QUESTIONS,
                                AWSAllQuestions.CLOUD_MONITORING_QUESTIONS,
                                AWSAllQuestions.VPC_NETWORKING_QUESTIONS,
                                AWSAllQuestions.SECURITY_COMPLIANCE_QUESTIONS,
                                AWSAllQuestions.ML_QUESTIONS,
                                AWSAllQuestions.ACCOUNT_BILLING_SUPPORT_QUESTIONS,
                                AWSAllQuestions.ADVANCED_IDENTITY_QUESTIONS,
                                AWSAllQuestions.OTHER_SERVICES_QUESTIONS,
                                AWSAllQuestions.ARCHITECTURE_ECOSYSTEM_QUESTIONS,
                                AWSAllQuestions.FOLLOW_UP_QUESTIONS };
                AWSRevision aws = new AWSRevision(questionBank);
                aws.runQuiz();
        }
}

