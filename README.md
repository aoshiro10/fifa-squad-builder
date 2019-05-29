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

The initial population is represented as a List of [Squads](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Squad.java) with randomly selected players from the Ultimate team database. The number of squads is determined by the population size parameter. The intial population is created using the private static function found in GeneticAlgorithm called ```getInitialSquads()```.

Params: 
- populationSize : int = number of squads in each generation population

#### Fitness function

The fitness function is represented by the chemistry of each squad, an attribute from the [Links](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Link.java) formed between [Players](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Player.java) in specific [Positions](https://github.com/aoshiro10/fifa-squad-builder/blob/master/src/main/java/Position.java). To find the chemistry of the squad call ```getChemistry()``` method.  

#### Selection

#TODO

#### Crossover


#### Mutation

