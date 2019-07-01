# Fifa Squad Builder

Using genetic algorithm to build a Fifa Ultimate Team squad.

## Running the Program

#TODO

## Genetic Algorithm

More about [Genetic Algorithm](https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3)

The Genetic Algorithm has 5 main components:
1. Initial population
2. Fitness function
3. Selection
4. Crossover
5. Mutation

Each of these components were adapted to create the best Fifa ultimate team squad. More about [Fifa ultimate team](https://www.ea.com/games/fifa/fifa-19/ultimate-team/beginners-guide-fut).

#### Initial Population

The initial population is represented as a List of [Squads](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Squad.java) with randomly selected players from the Ultimate team database. The number of squads is determined by the population size parameter. The intial population is created using the called ```getInitialSquads()``` in [GeneticAlgorithm](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/GeneticAlgorithm.java).

Params: 
- populationSize : int = number of squads in each generation population

#### Fitness function

The fitness function is represented by the chemistry of each squad, an attribute from the [Links](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Link.java) formed between [Players](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Player.java) in specific [Positions](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Position.java). To find the chemistry of the squad call ```getChemistry()``` method.  

#### Selection

Selection is based on probability. The higher the fitness value for a particular squad, the more likely it is to be selected. This is possible by creating a squad pool with all squads given their fitness. Squads are selected at random from the pool. 

#### Crossover

Once a squad is selected from the selection process, it goes through the crossover step with another squad. The problem is that squads from different countries/clubs do not match well. Therefore, a squad will only perform the crossover process with a squad of the same country or club. This is done by picking another squad from the squad pool at random until a squad of the same country or club is found. Once found, the squads are combined to create a new squad with players from both teams. There are equal chances of a player to come from either squad. 

#### Mutation

Mutation is the process of picking a random player from a squad and swapping him with a random player from the Ultimate Team database. Mutation rate is initially set to 25%, but this value can be adjusted in the [main function](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/SquadBuilder.java).
