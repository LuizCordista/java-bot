# README.md

## Project Overview

This project is a Discord bot that scrapes valorant players statistics from tracker.gg. It displays these statistics in the bot. The bot is implemented in Java and uses Maven for dependency management.

## Project Setup

This project uses Java and Maven. Make sure you have them installed on your system.

1. Clone the repository:

```BASH
$ git clone https://github.com/LuizCordista/java-bot.git
```

2. Navigate into the project directory:

```BASH
$ cd java-bot
```

## Environment Variables

This project uses environment variables to manage sensitive data. The `.env` file is used to store these variables.

1. Create a `.env` file in the root directory of the project.

2. Add the following line to the `.env` file:

```DOTENV
DISCORD_TOKEN=your_discord_token
```

Replace `your_discord_token` with your actual Discord token.

## Running the Application

1. Compile the project:

```bash
mvn clean install
```

2. Run the application:

```bash
java -jar target/tracker-1.0-SNAPSHOT.jar
```

## Using the Bot

To use the bot, type the following command in the Discord chat:

```bash
!rank Nick#Tag
```

Replace `Nick#Tag` with the actual nickname and tag of the player you want to get statistics for.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
